package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.DevicesAdapter
import kotlinx.android.synthetic.main.amd_layout.*

class AmdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.amd_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(AmdDevices.instance.list)
    }


    fun setupRecycler(devices: ArrayList<Device>) {
        amdDevicesRecycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        amdDevicesRecycle.layoutManager = layoutManager
        amdDevicesRecycle.adapter = DevicesAdapter(devices)
    }

}