package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import java.util.*

class AmdDevices: VendorDevices {

    override var devicesCount:Int = 0


    override val list: ArrayList<Device> = arrayListOf(
        Amd(0, "RX 460", hashMapOf(
            Pair(" ", 0.0)),400,0),
        Amd(1, "RX 470",hashMapOf(
            Pair("", 0.0)), 700,0),
        Amd(2, "RX 480", hashMapOf(
            Pair("", 0.0)),830,0),
        Amd(3, "RX 550", hashMapOf(
            Pair("", 0.0)),400,0),
        Amd(4, "RX 560",hashMapOf(
            Pair("", 0.0)), 420,0),
        Amd(5, "RX 570",hashMapOf(
            Pair("", 0.0)), 730,0),
        Amd(6, "RX 580",hashMapOf(
            Pair("", 0.0)), 830,0),
        Amd(7, "RX Frontier",hashMapOf(
            Pair("", 0.0)), 1720,0),
        Amd(8, "RX FuryX",hashMapOf(
            Pair("", 0.0)), 850,0),
        Amd(9, "RX Vega 56", hashMapOf(
            Pair("", 0.0)),1720,0),
        Amd(10, "RX Vega 64",hashMapOf(
            Pair("", 0.0)), 1800,0),
        Amd(11, "RX Vega VII",hashMapOf(
            Pair("", 0.0)), 2800,0)
        )


    companion object {
        val instance = AmdDevices()

    }
}