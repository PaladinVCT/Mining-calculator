package by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.item_device.view.*

class DevicesAdapterNvidia(private val context: Context,
                           private val devices: ArrayList<Device>
) : RecyclerView.Adapter<DevicesViewHolderNvidia>() {

    lateinit var initialRigSetup: InitialRigSetup

    interface InitialRigSetup {
        fun setupRigDevices(instance: VendorDevices, deviceNameLayoutId:Int, deviceCountLayoutId:Int, counterTextView:Int)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        initialRigSetup = context as InitialRigSetup
        super.onAttachedToRecyclerView(recyclerView)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DevicesViewHolderNvidia {



        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_device, viewGroup, false)
        val holder = DevicesViewHolderNvidia(view)


        val plus = holder.itemView.findViewById<ImageButton>(R.id.plusButton)
        val minus = holder.itemView.findViewById<ImageButton>(R.id.minusButton)
        val deviceCounter = holder.itemView.findViewById<EditText>(R.id.deviceCounter)

        plus.setOnClickListener {
            deviceCounter.setText((++NvidiaDevices.instance.list.get(holder.adapterPosition).count).toString())
            holder.itemView.item_device.background = it.context.getDrawable(R.drawable.selected_device_shape)
            ++NvidiaDevices.instance.devicesCount
            initialRigSetup.setupRigDevices(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)

            holder.itemView.invalidate()

            Log.e("AAA", NvidiaDevices.instance.devicesCount.toString())
        }

        minus.setOnClickListener {
            if (NvidiaDevices.instance.list.get(holder.adapterPosition).count > 0) {
                deviceCounter.setText((--NvidiaDevices.instance.list.get(holder.adapterPosition).count).toString())
                --NvidiaDevices.instance.devicesCount
                initialRigSetup.setupRigDevices(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)

                holder.itemView.invalidate()

                Log.e("AAA", NvidiaDevices.instance.devicesCount.toString())
            }
            if (NvidiaDevices.instance.list.get(holder.adapterPosition).count == 0) {
                holder.itemView.item_device.background = it.context.getDrawable(R.drawable.device_shape)
                initialRigSetup.setupRigDevices(NvidiaDevices.instance,R.id.deviceNameLayoutNvidia,R.id.deviceCountLayoutNvidia,R.id.rigDeviceCounterNvidia)

                holder.itemView.invalidate()

            }
        }
        return holder
    }

    override fun onBindViewHolder(devicesViewHolderNvidia: DevicesViewHolderNvidia, id: Int) {

        devicesViewHolderNvidia.bind(devices.get(id))
    }

    override fun getItemCount(): Int {
        return devices.size
    }
}