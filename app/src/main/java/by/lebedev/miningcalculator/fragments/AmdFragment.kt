package by.lebedev.miningcalculator.fragments

import android.content.Context
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

class AmdFragment() : Fragment()  {


//        rigDeviceCounter.setText(AmdDevices.instance.devicesCount.toString())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.amd_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(AmdDevices.instance.list)


        calculateRigButton.setOnClickListener {

        }




    }


    fun setupRecycler(devices: ArrayList<Device>) {
        amdDevicesRecycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        amdDevicesRecycle.layoutManager = layoutManager
        amdDevicesRecycle.adapter = DevicesAdapter(this.context!!,devices)



//        amdDevicesRecycle.setOnHoverListener(object :View.OnHoverListener{
//            override fun onHover(v: View?, event: MotionEvent?): Boolean {
//
//                rigDeviceCounter.setText(AmdDevices.instance.devicesCount.toString())
//
//                return false
//            }
//        })

//        amdDevicesRecycle.setOnTouchListener(object :View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                rigDeviceCounter.setText(AmdDevices.instance.devicesCount.toString())
//return false
//            }
//
//        })

//        amdDevicesRecycle.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
//            override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {
//                        }
//
//            override fun onInterceptTouchEvent(p0: RecyclerView, p1: MotionEvent): Boolean {
//                rigDeviceCounter.setText(AmdDevices.instance.devicesCount.toString())
//                return false
//            }
//
//            override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })

    }




}


