package by.lebedev.domain.collections

import by.lebedev.domain.entities.Device
import java.util.ArrayList

interface VendorDevices {

    var devicesCount: Int

    val list: ArrayList<Device>

}