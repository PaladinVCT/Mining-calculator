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
import by.lebedev.domain.usecase.DeleteSelectedConfigUseCase
import by.lebedev.domain.usecase.LoadNvidiaRigConfigUseCase
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia.DevicesAdapterNvidia
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.nvidia_layout.*

class NvidiaFragment : Fragment() {

    private val vendor = "Nvidia"
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
                intent.putExtra("selectedItem", 1)
                intent.putExtra("hashrate", 1000.0)
                intent.putExtra("device", "RIG")

                it.context.startActivity(intent)
            } else {
                Toast.makeText(
                    this.context, "Please try to add some devices",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        loadRigButtonNvidia.setOnClickListener {

            var selectedItem = -1

            val x = LoadNvidiaRigConfigUseCase().execute(view.context, vendor)
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


                            .setPositiveButton("Load", { dialog, _ ->
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
                                    view.context, "Configuration loaded",
                                    Toast.LENGTH_SHORT
                                ).show()

                                dialog.cancel()
                            })
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



        saveRigButtonNvidia.setOnClickListener {
            saveConfigNvidia.saveNvidia()
        }



    }


    fun setupRecycler(devices: ArrayList<Device>) {
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
