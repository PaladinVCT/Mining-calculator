package by.lebedev.domain.transformators

import by.lebedev.domain.entities.Device

interface DevicesCount {

    fun execute(list:ArrayList<Device>) :Int

}