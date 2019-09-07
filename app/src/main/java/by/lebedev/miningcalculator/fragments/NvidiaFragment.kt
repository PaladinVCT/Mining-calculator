package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.CoinProfitabilityRigTransformator
import by.lebedev.domain.transformators.CoinProfitabilityStringTransformator
import by.lebedev.domain.transformators.HashPowerAggregator
import by.lebedev.domain.usecase.GetAllProfitableCoinsUseCaseNvidia
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.devicesrecycler.amd.DevicesAdapterAMD
import by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia.DevicesAdapterNvidia
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.*
import kotlinx.android.synthetic.main.nvidia_layout.*

class NvidiaFragment : Fragment() {
    interface SetupDevices {
        fun setupAtStartup(instance: VendorDevices, deviceNameLayoutId:Int, deviceCountLayoutId:Int, counterTextView:Int)
    }

    interface ClearAllDevices {
        fun clear(instance: VendorDevices,deviceNameLayoutId:Int,deviceCountLayoutId:Int,counterTextView:Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.nvidia_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(NvidiaDevices.instance.list)
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)

        val clearAllDevices = context as ClearAllDevices

        clearRigButtonNvidia.setOnClickListener {
            clearAllDevices.clear(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)
            setupRecycler(NvidiaDevices.instance.list)

        }


        calculateRigButtonNvidia.setOnClickListener {


//            val d = GetAllProfitableCoinsUseCaseNvidia().fetch(1, 1000.0, "GPU")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//
////                    earningsProgressBar.visibility = View.INVISIBLE
////
//                    val globalProfit = result
//
//                    val profit = CoinProfitabilityRigTransformator().execute(globalProfit, 1000.0)
//                    Log.e("AAA", profit.get(0).hashrateAuto)
//                    Log.e("AAA", profit.get(1).hashrateAuto)
//                    Log.e("AAA", profit.get(2).hashrateAuto)
//                    Log.e("AAA", profit.get(3).hashrateAuto)
//
//
////                    val profitArrayString = CoinProfitabilityStringTransformator().execute(profit)
////                    setupRecycler(profitArrayString)
//
//
//                }, {
//                    Log.e("AAA", it.message)
//                })



            val intent = Intent(this.context, EarningsActivity::class.java)
            intent.putExtra("selectedItem",1)
            intent.putExtra("hashrate", 1000.0)
            intent.putExtra("device","RIG")

            it.context.startActivity(intent)

        }


    }


    fun setupRecycler(devices: ArrayList<Device>) {
        devicesRecycleNvidia.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        devicesRecycleNvidia.layoutManager = layoutManager
        devicesRecycleNvidia.adapter =
            DevicesAdapterNvidia(this.context!!, devices)

    }

    override fun onResume() {
        super.onResume()
        val setupDevicesAtStartup = context as SetupDevices
        setupDevicesAtStartup.setupAtStartup(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)
    }


}
