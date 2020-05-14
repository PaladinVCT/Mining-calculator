package by.lebedev.miningcalculator.recyclers.devicesrecycler.amd

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import by.lebedev.domain.collections.AmdImages
import by.lebedev.domain.entities.Device
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.item_device.view.*


class DevicesViewHolderAMD(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun bind(device: Device) {

        itemView.deviceImage.setImageResource(AmdImages.instance.list.get(device.id))
        itemView.deviceName.text = device.name
        itemView.deviceCounter.setText(device.count.toString())

        if (device.count > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                itemView.item_device.background = itemView.context.getDrawable(R.drawable.selected_device_shape)
            }
        } else {
            itemView.item_device.background = itemView.context.getDrawable(R.drawable.device_shape)
        }
    }
}