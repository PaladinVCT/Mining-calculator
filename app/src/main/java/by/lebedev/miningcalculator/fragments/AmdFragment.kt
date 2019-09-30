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
import by.lebedev.domain.transformators.HashPowerAggregator
import by.lebedev.domain.usecase.DeleteSelectedConfigUseCase
import by.lebedev.domain.usecase.LoadAmdRigConfigUseCase
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.recyclers.devicesrecycler.amd.DevicesAdapterAMD
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.amd_layout.*
import androidx.recyclerview.widget.LinearSnapHelper
import by.lebedev.miningcalculator.R


class AmdFragment() : Fragment() {

    private val vendor = "Amd"
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
        fun clear(instance: VendorDevices, deviceNameLayoutId: Int, deviceCountLayoutId: Int, counterTextView: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
                intent.putExtra("selectedItem", 0)
                intent.putExtra("hashrate", HashPowerAggregator().execute(AmdDevices.instance.list))
                intent.putExtra("device", "GPU")

                it.context.startActivity(intent)
            } else {
                Toast.makeText(
                    this.context, "Please try to add some devices",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        loadRigButtonAMD.setOnClickListener {

            var selectedItem = -1

            LoadAmdRigConfigUseCase().execute(view.context, vendor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if (it.size == 0) {
                        Toast.makeText(
                            view.context, "Configuration list is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val namesArray = ConfigArrayToStringTransformator().execute(it)

                        val builder = AlertDialog.Builder(view.context)
                            .setTitle("Select your Rig config")
                            .setIcon(R.drawable.rigicon)
                            .setCancelable(true)

                            .setSingleChoiceItems(namesArray, -1,
                                { _, item ->

                                    selectedItem = item

                                })


                            .setPositiveButton("Load") { dialog, _ ->
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
                                    view.context, "Configuration loaded",
                                    Toast.LENGTH_SHORT
                                ).show()

                                dialog.cancel()
                            }
                            .setNegativeButton("Cancel", { dialog, _ ->

                                dialog.cancel()
                            })
                            .setNeutralButton("Delete", { dialog, _ ->

                                DeleteSelectedConfigUseCase().execute(view.context, it.get(selectedItem).name, vendor)

                                dialog.cancel()

                                Toast.makeText(
                                    view.context, "Configuration deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                        val alert = builder.create()
                        alert.show()


                    }
                }, {
                    Log.e("AAA", it.message)
                })
        }


        saveRigButtonAMD.setOnClickListener {
            saveConfigAMD.saveAMD()
        }


    }


    fun setupRecycler(devices: ArrayList<Device>) {

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