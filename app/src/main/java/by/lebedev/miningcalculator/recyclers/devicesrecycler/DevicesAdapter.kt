package by.lebedev.miningcalculator.recyclers.devicesrecycler

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.item_device.view.*

class DevicesAdapter(
    private val devices: ArrayList<Device>
) : RecyclerView.Adapter<DevicesViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DevicesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_device, viewGroup, false)
        val holder = DevicesViewHolder(view)


        val plus = holder.itemView.findViewById<ImageButton>(R.id.plusButton)
        val minus = holder.itemView.findViewById<ImageButton>(R.id.minusButton)
        val deviceCounter = holder.itemView.findViewById<EditText>(R.id.deviceCounter)

        plus.setOnClickListener {
            deviceCounter.setText((++AmdDevices.instance.list.get(holder.adapterPosition).count).toString())
            holder.itemView.item_coin.background = it.context.getDrawable(R.drawable.selected_device_shape)
        }

        minus.setOnClickListener {
            if (AmdDevices.instance.list.get(holder.adapterPosition).count > 0) {
                deviceCounter.setText((--AmdDevices.instance.list.get(holder.adapterPosition).count).toString())
            }
            if (AmdDevices.instance.list.get(holder.adapterPosition).count == 0) {
                holder.itemView.item_coin.background = it.context.getDrawable(R.drawable.device_shape)
            }

        }


        return holder
    }

    override fun onBindViewHolder(devicesViewHolder: DevicesViewHolder, id: Int) {

        devicesViewHolder.bind(devices.get(id))
    }

    override fun getItemCount(): Int {
        return devices.size
    }
}