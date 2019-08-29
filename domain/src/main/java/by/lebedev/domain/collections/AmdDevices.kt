package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import java.util.*

class AmdDevices {

    val list: ArrayList<Amd> = arrayListOf(
        Amd(0, "RX 460", 400),
        Amd(0, "RX 470", 700),
        Amd(0, "RX 480", 800),
        Amd(0, "RX 550", 400),
        Amd(0, "RX 560", 420),
        Amd(0, "RX 570", 720),
        Amd(0, "RX 580", 900),
        Amd(0, "RX Frontier", 2000),
        Amd(0, "RX FuryX", 850),
        Amd(0, "RX Vega 56", 2000),
        Amd(0, "RX Vega 64", 2000)

    )


    companion object {
        val instance = AmdDevices()
    }
}