package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.HashPowerAggregator
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.MainActivity
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.DevicesAdapter
import kotlinx.android.synthetic.main.amd_layout.*

class AmdFragment() : Fragment() {

    interface SetupDevicesAtStartup {
        fun execute()
    }

    interface ClearAllDevices {
        fun clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.amd_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(AmdDevices.instance.list)
        val setupDevicesAtStartup = context as SetupDevicesAtStartup
        setupDevicesAtStartup.execute()

        val clearAllDevices = context as ClearAllDevices

        clearRigButton.setOnClickListener {
            clearAllDevices.clear()
            setupRecycler(AmdDevices.instance.list)

        }


        calculateRigButton.setOnClickListener {

            val intent = Intent(this.context, EarningsActivity::class.java)
            intent.putExtra("selectedItem",0)
            intent.putExtra("hashrate",HashPowerAggregator().execute(AmdDevices.instance.list))
            intent.putExtra("device","GPU")

            it.context.startActivity(intent)

        }


    }


    fun setupRecycler(devices: ArrayList<Device>) {
        amdDevicesRecycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        amdDevicesRecycle.layoutManager = layoutManager
        amdDevicesRecycle.adapter = DevicesAdapter(this.context!!, devices)

    }

    override fun onResume() {
        super.onResume()
        val setupDevicesAtStartup = context as SetupDevicesAtStartup
        setupDevicesAtStartup.execute()
    }


}


