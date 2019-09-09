package by.lebedev.miningcalculator.recyclers.devicesrecycler.amd

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.item_device.view.*

class DevicesAdapterAMD(private val context: Context,
                        private val devices: ArrayList<Device>
) : androidx.recyclerview.widget.RecyclerView.Adapter<DevicesViewHolderAMD>() {

    lateinit var initialRigSetup: InitialRigSetup

    interface InitialRigSetup {
        fun setupRigDevices(instance: VendorDevices,deviceNameLayoutId:Int,deviceCountLayoutId:Int,counterTextView:Int)
    }




    override fun onAttachedToRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        initialRigSetup = context as InitialRigSetup
        super.onAttachedToRecyclerView(recyclerView)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DevicesViewHolderAMD {



        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_device, viewGroup, false)
        val holder = DevicesViewHolderAMD(view)


        val plus = holder.itemView.findViewById<ImageButton>(R.id.plusButton)
        val minus = holder.itemView.findViewById<ImageButton>(R.id.minusButton)
        val deviceCounter = holder.itemView.findViewById<EditText>(R.id.deviceCounter)

        plus.setOnClickListener {
            deviceCounter.setText((++AmdDevices.instance.list.get(holder.adapterPosition).count).toString())
            holder.itemView.item_device.background = it.context.getDrawable(R.drawable.selected_device_shape)
            ++AmdDevices.instance.devicesCount
            initialRigSetup.setupRigDevices(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)

            holder.itemView.invalidate()

            Log.e("AAA", AmdDevices.instance.devicesCount.toString())
        }

        minus.setOnClickListener {
            if (AmdDevices.instance.list.get(holder.adapterPosition).count > 0) {
                deviceCounter.setText((--AmdDevices.instance.list.get(holder.adapterPosition).count).toString())
                --AmdDevices.instance.devicesCount
                initialRigSetup.setupRigDevices(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)

                holder.itemView.invalidate()

                Log.e("AAA", AmdDevices.instance.devicesCount.toString())
            }
            if (AmdDevices.instance.list.get(holder.adapterPosition).count == 0) {
                holder.itemView.item_device.background = it.context.getDrawable(R.drawable.device_shape)
                initialRigSetup.setupRigDevices(AmdDevices.instance,R.id.deviceNameLayoutAMD,R.id.deviceCountLayoutAMD,R.id.rigDeviceCounterAMD)

                holder.itemView.invalidate()

            }
        }
        return holder
    }

    override fun onBindViewHolder(devicesViewHolderAMD: DevicesViewHolderAMD, id: Int) {

        devicesViewHolderAMD.bind(devices.get(id))
    }

    override fun getItemCount(): Int {
        return devices.size
    }
}