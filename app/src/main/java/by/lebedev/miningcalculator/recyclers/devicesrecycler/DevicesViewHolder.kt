package by.lebedev.miningcalculator.recyclers.devicesrecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import by.lebedev.domain.collections.AmdImages
import by.lebedev.domain.entities.Device
import kotlinx.android.synthetic.main.item_device.view.*


class DevicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(device: Device) {

        itemView.deviceImage.setImageResource(AmdImages.instance.list.get(device.id))
        itemView.deviceName.setText(device.name)
        itemView.deviceCounter.setText(device.count.toString())
    }
}