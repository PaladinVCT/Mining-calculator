package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import java.util.*

class AmdDevices: VendorDevices {

    override var devicesCount:Int = 0


    override val list: ArrayList<Device> = arrayListOf(
        Amd(0, "RX 460", 400,0,null!!),
        Amd(1, "RX 470", 700,0,null!!),
        Amd(2, "RX 480", 830,0,null!!),
        Amd(3, "RX 550", 400,0,null!!),
        Amd(4, "RX 560", 420,0,null!!),
        Amd(5, "RX 570", 730,0,null!!),
        Amd(6, "RX 580", 830,0,null!!),
        Amd(7, "RX Frontier", 1720,0,null!!),
        Amd(8, "RX FuryX", 850,0,null!!),
        Amd(9, "RX Vega 56", 1720,0,null!!),
        Amd(10, "RX Vega 64", 1800,0,null!!),
        Amd(11, "RX Vega VII", 2800,0,null!!)

    )


    companion object {
        val instance = AmdDevices()

    }
}