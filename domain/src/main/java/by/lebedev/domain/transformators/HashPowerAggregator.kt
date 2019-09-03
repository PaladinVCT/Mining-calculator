package by.lebedev.domain.transformators

import by.lebedev.domain.entities.Device

class HashPowerAggregator {

    var hashPower = 0.0

    fun execute(devices: ArrayList<Device>): Double {

        for (i in 0 until devices.size) {

            hashPower += devices.get(i).hashrate*devices.get(i).count
        }
        return hashPower
    }

}