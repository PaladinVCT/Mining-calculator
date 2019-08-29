package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.miningcalculator.DevicesPageAdapter
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.devices_layout.*


class DevicesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.devices_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = DevicesPageAdapter(childFragmentManager)

        devicesViewpager.adapter = fragmentAdapter

        devicesTab.setupWithViewPager(devicesViewpager)

    }

}