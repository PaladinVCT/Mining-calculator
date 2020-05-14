package by.lebedev.miningcalculator.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.ConfigArrayToStringTransformator
import by.lebedev.domain.transformators.RigDevicesCount
import by.lebedev.domain.usecase.DeleteSelectedConfigUseCaseImpl
import by.lebedev.domain.usecase.LoadAmdRigConfigUseCaseImpl
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.recyclers.devicesrecycler.amd.DevicesAdapterAMD
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.amd_layout.*
import androidx.recyclerview.widget.LinearSnapHelper
import by.lebedev.miningcalculator.R

private const val VENDOR = "Amd"
const val SELECTED_ITEM = "selectedItem"
const val HASHRATE = "hashrate"
const val DEVICE = "device"
const val TAG = "tag"

class AmdFragment : Fragment() {

    private val snapHelper = LinearSnapHelper()
    lateinit var mAdView: AdView

    interface SetupDevices {
        fun setupAtStartup(
            instance: VendorDevices,
            deviceNameLayoutId: Int,
            deviceCountLayoutId: Int,
            counterTextView: Int
        )
    }

    interface SaveConfigAMD {
        fun saveAMD()
    }

    interface ClearAllDevices {
        fun clear(
            instance: VendorDevices,
            deviceNameLayoutId: Int,
            deviceCountLayoutId: Int,
            counterTextView: Int
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.amd_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdView = adViewAmd
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        snapHelper.attachToRecyclerView(devicesRecycleAMD)

        setupRecycler(AmdDevices.instance.list)
        val saveConfigAMD = context as SaveConfigAMD
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(
            AmdDevices.instance,
            R.id.deviceNameLayoutAMD,
            R.id.deviceCountLayoutAMD,
            R.id.rigDeviceCounterAMD
        )

        val clearAllDevices = context as ClearAllDevices

        clearRigButtonAMD.setOnClickListener {
            clearAllDevices.clear(
                AmdDevices.instance,
                R.id.deviceNameLayoutAMD,
                R.id.deviceCountLayoutAMD,
                R.id.rigDeviceCounterAMD
            )
            setupRecycler(AmdDevices.instance.list)

        }


        calculateRigButtonAMD.setOnClickListener {

            if (RigDevicesCount().execute(AmdDevices.instance.list) != 0) {
                val intent = Intent(this.context, EarningsActivity::class.java)

                intent.putExtra(SELECTED_ITEM, 1)
                intent.putExtra(HASHRATE, 1000.0)
                intent.putExtra(DEVICE, getString(R.string.Rigamd))


                it.context.startActivity(intent)
            } else {
                Toast.makeText(
                    this.context, getString(R.string.please_add_device),
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        loadRigButtonAMD.setOnClickListener {

            var selectedItem = -1

            LoadAmdRigConfigUseCaseImpl().execute(view.context, VENDOR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if (it.size == 0) {
                        Toast.makeText(
                            view.context, getString(R.string.config_list_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val namesArray = ConfigArrayToStringTransformator().execute(it)

                        val builder = AlertDialog.Builder(view.context)
                            .setTitle(getString(R.string.select_rig_config))
                            .setIcon(R.drawable.rigicon)
                            .setCancelable(true)

                            .setSingleChoiceItems(
                                namesArray, -1
                            ) { _, item ->
                                selectedItem = item
                            }


                            .setPositiveButton(getString(R.string.load)) { dialog, _ ->
                                if (selectedItem != -1) {

                                    for (i in 0 until AmdDevices.instance.list.size) {
                                        AmdDevices.instance.list.get(i).count =
                                            it.get(selectedItem).numberDevices.get(i)
                                    }

                                    AmdDevices.instance.devicesCount =
                                        RigDevicesCount().execute(AmdDevices.instance.list)


                                    setupDevicesAtStartup.setupAtStartup(
                                        AmdDevices.instance,
                                        R.id.deviceNameLayoutAMD,
                                        R.id.deviceCountLayoutAMD,
                                        R.id.rigDeviceCounterAMD
                                    )

                                    setupRecycler(AmdDevices.instance.list)
                                }

                                Toast.makeText(
                                    view.context, getString(R.string.config_loaded),
                                    Toast.LENGTH_SHORT
                                ).show()

                                dialog.cancel()
                            }
                            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->

                                dialog.cancel()
                            }
                            .setNeutralButton(getString(R.string.delete)) { dialog, _ ->

                                DeleteSelectedConfigUseCaseImpl().execute(
                                    view.context,
                                    it.get(selectedItem).name,
                                    VENDOR
                                )

                                dialog.cancel()

                                Toast.makeText(
                                    view.context, getString(R.string.config_deleted),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        val alert = builder.create()
                        alert.show()


                    }
                }, {
                    Log.e(TAG, it.localizedMessage)
                })
        }


        saveRigButtonAMD.setOnClickListener {
            saveConfigAMD.saveAMD()
        }


    }


    private fun setupRecycler(devices: ArrayList<Device>) {

        devicesRecycleAMD.setHasFixedSize(true)
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        devicesRecycleAMD.layoutManager = layoutManager
        devicesRecycleAMD.adapter =
            DevicesAdapterAMD(this.context!!, devices)

    }

    override fun onResume() {
        super.onResume()
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(
            AmdDevices.instance,
            R.id.deviceNameLayoutAMD,
            R.id.deviceCountLayoutAMD,
            R.id.rigDeviceCounterAMD
        )
    }
}