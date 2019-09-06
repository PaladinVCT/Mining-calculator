package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.HashPowerAggregator
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.amd.DevicesAdapterAMD
import kotlinx.android.synthetic.main.amd_layout.*

class AmdFragment() : Fragment() {

    interface SetupDevices {
        fun setupAtStartup(instance: VendorDevices,deviceNameLayoutId:Int,deviceCountLayoutId:Int,counterTextView:Int)
    }

    interface ClearAllDevices {
        fun clear(instance: VendorDevices, deviceNameLayoutId:Int, deviceCountLayoutId:Int, counterTextView:Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.amd_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(AmdDevices.instance.list)
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)

        val clearAllDevices = context as ClearAllDevices

        clearRigButtonAMD.setOnClickListener {
            clearAllDevices.clear(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)
            setupRecycler(AmdDevices.instance.list)

        }


        calculateRigButtonAMD.setOnClickListener {

            val intent = Intent(this.context, EarningsActivity::class.java)
            intent.putExtra("selectedItem",0)
            intent.putExtra("hashrate",HashPowerAggregator().execute(AmdDevices.instance.list))
            intent.putExtra("device","GPU")

            it.context.startActivity(intent)

        }


    }


    fun setupRecycler(devices: ArrayList<Device>) {
        devicesRecycleAMD.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        devicesRecycleAMD.layoutManager = layoutManager
        devicesRecycleAMD.adapter =
            DevicesAdapterAMD(this.context!!, devices)

    }

    override fun onResume() {
        super.onResume()
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)
    }


}


