package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import java.util.*

class AmdDevices {

    var devicesCount:Int = 0


    val list: ArrayList<Device> = arrayListOf(
        Amd(0, "RX 460", 400,0),
        Amd(1, "RX 470", 700,0),
        Amd(2, "RX 480", 830,0),
        Amd(3, "RX 550", 400,0),
        Amd(4, "RX 560", 420,0),
        Amd(5, "RX 570", 730,0),
        Amd(6, "RX 580", 830,0),
        Amd(7, "RX Frontier", 1720,0),
        Amd(8, "RX FuryX", 850,0),
        Amd(9, "RX Vega 56", 1720,0),
        Amd(10, "RX Vega 64", 1800,0)

    )


    companion object {
        val instance = AmdDevices()

    }
}