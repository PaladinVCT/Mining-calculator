package by.lebedev.domain.transformators

import by.lebedev.domain.entities.Device

class RigDevicesCount : DevicesCount {
    override fun execute(list: ArrayList<Device>): Int {
        var count = 0

        for (i in 0 until list.size) {
            count += list[i].count
        }
        return count
    }
}