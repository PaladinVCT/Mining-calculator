package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearSnapHelper
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.*
import by.lebedev.domain.usecase.DeleteSelectedConfigUseCaseImpl
import by.lebedev.domain.usecase.LoadNvidiaRigConfigUseCaseImpl
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia.DevicesAdapterNvidia
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.nvidia_layout.*

private const val VENDOR = "Nvidia"

class NvidiaFragment : Fragment() {
    private lateinit var mInterstitialAd: InterstitialAd
    private val snapHelper = LinearSnapHelper()
    lateinit var mAdView : AdView

    interface SetupDevices {
        fun setupAtStartup(
            instance: VendorDevices,
            deviceNameLayoutId: Int,
            deviceCountLayoutId: Int,
            counterTextView: Int
        )
    }

    interface SaveConfigNvidia {
        fun saveNvidia()
    }

    interface ClearAllDevices {
        fun clear(instance: VendorDevices, deviceNameLayoutId: Int, deviceCountLayoutId: Int, counterTextView: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.nvidia_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mInterstitialAd = InterstitialAd(requireContext())
        mInterstitialAd.adUnitId = resources.getString(R.string.interstitial_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mAdView = adViewNvidia
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        snapHelper.attachToRecyclerView(devicesRecycleNvidia)

        setupRecycler(NvidiaDevices.instance.list)
        val saveConfigNvidia = context as SaveConfigNvidia
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(
            NvidiaDevices.instance,
            R.id.deviceNameLayoutNvidia,
            R.id.deviceCountLayoutNvidia,
            R.id.rigDeviceCounterNvidia
        )

        val clearAllDevices = context as ClearAllDevices

        clearRigButtonNvidia.setOnClickListener {
            clearAllDevices.clear(
                NvidiaDevices.instance,
                R.id.deviceNameLayoutNvidia,
                R.id.deviceCountLayoutNvidia,
                R.id.rigDeviceCounterNvidia
            )
            setupRecycler(NvidiaDevices.instance.list)

        }


        calculateRigButtonNvidia.setOnClickListener {

            if (RigDevicesCount().execute(NvidiaDevices.instance.list) != 0) {
                val intent = Intent(this.context, EarningsActivity::class.java)
                intent.putExtra(SELECTED_ITEM, 1)
                intent.putExtra(HASHRATE, 1000.0)
                intent.putExtra(DEVICE, getString(R.string.RIG))

                it.context.startActivity(intent)
            } else {
                Toast.makeText(
                    this.context, getString(R.string.please_add_device),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        loadRigButtonNvidia.setOnClickListener {

            var selectedItem = -1

            val x = LoadNvidiaRigConfigUseCaseImpl().execute(view.context, VENDOR)
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
                            .setTitle(getString(R.string.select_your_device))
                            .setIcon(R.drawable.rigicon)
                            .setCancelable(true)

                            .setSingleChoiceItems(namesArray, -1
                            ) { _, item ->

                                selectedItem = item

                            }


                            .setPositiveButton(getString(R.string.load)) { dialog, _ ->
                                if (selectedItem != -1) {

                                    for (i in 0 until NvidiaDevices.instance.list.size) {
                                        NvidiaDevices.instance.list.get(i).count =
                                            it.get(selectedItem).numberDevices.get(i)
                                    }

                                    NvidiaDevices.instance.devicesCount =
                                        RigDevicesCount().execute(NvidiaDevices.instance.list)


                                    setupDevicesAtStartup.setupAtStartup(
                                        NvidiaDevices.instance,
                                        R.id.deviceNameLayoutNvidia,
                                        R.id.deviceCountLayoutNvidia,
                                        R.id.rigDeviceCounterNvidia
                                    )

                                    setupRecycler(NvidiaDevices.instance.list)
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

                                DeleteSelectedConfigUseCaseImpl().execute(view.context, it.get(selectedItem).name, VENDOR)

                                dialog.cancel()

                                Toast.makeText(
                                    view.context, getString(R.string.config_loaded),
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

        saveRigButtonNvidia.setOnClickListener {
            saveConfigNvidia.saveNvidia()
        }
    }


    private fun setupRecycler(devices: ArrayList<Device>) {
        devicesRecycleNvidia.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation =LinearLayoutManager.HORIZONTAL
        devicesRecycleNvidia.layoutManager = layoutManager
        devicesRecycleNvidia.adapter =
            DevicesAdapterNvidia(this.context!!, devices)
    }

    override fun onResume() {
        super.onResume()
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(
            NvidiaDevices.instance,
            R.id.deviceNameLayoutNvidia,
            R.id.deviceCountLayoutNvidia,
            R.id.rigDeviceCounterNvidia
        )
    }
}