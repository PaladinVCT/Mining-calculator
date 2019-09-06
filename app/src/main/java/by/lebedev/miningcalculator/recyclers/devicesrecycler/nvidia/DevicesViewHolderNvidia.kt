package by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.AmdImages
import by.lebedev.domain.collections.NvidiaImages
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.fragments.AmdFragment
import kotlinx.android.synthetic.main.item_device.view.*


class DevicesViewHolderNvidia(itemView: View) : RecyclerView.ViewHolder(itemView) {




    fun bind(device: Device) {


        itemView.deviceImage.setImageResource(NvidiaImages.instance.list.get(device.id))
        itemView.deviceName.setText(device.name)
        itemView.deviceCounter.setText(device.count.toString())

        if (device.count > 0) {
            itemView.item_device.background = itemView.context.getDrawable(R.drawable.selected_device_shape)
        } else {
            itemView.item_device.background = itemView.context.getDrawable(R.drawable.device_shape)
        }




    }
}