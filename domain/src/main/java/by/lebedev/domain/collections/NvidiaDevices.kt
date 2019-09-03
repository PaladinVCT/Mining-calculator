package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import by.lebedev.domain.entities.Nvidia
import java.util.*
import kotlin.collections.HashMap

class NvidiaDevices {

    var devicesCount: Int = 0


    val list: ArrayList<Device> = arrayListOf(
        Nvidia(
            0, "1050Ti", hashMapOf(
                Pair("Ethash", 13.9),
                Pair("Equihash(210,9)", 80.0),
                Pair("Zhash", 19.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 6.5),
                Pair("MTP", 0.0),
                Pair("X25X", 1.6),
                Pair("Lyra2REv3", 18.8),
                Pair("CuckooCycle", 1.8),
                Pair("PHI2", 2.4),
                Pair("NeoScrypt", 500.0),
                Pair("Skunkhash", 11.0),
                Pair("Cuckarood29", 0.0),
                Pair("TimeTravel10", 11.5),
                Pair("BCD", 8.5),
                Pair("BeamHashII", 8.5)
            ), 0
        ),
        Nvidia(
            1, "1060", hashMapOf(
                Pair("Ethash", 22.5),
                Pair("Equihash(210,9)", 120.0),
                Pair("Zhash", 32.5),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 9.4),
                Pair("MTP", 1.1),
                Pair("X25X", 2.4),
                Pair("Lyra2REv3", 26.5),
                Pair("CuckooCycle", 3.3),
                Pair("PHI2", 4.2),
                Pair("NeoScrypt", 680.0),
                Pair("Skunkhash", 18.0),
                Pair("Cuckarood29", 3.0),
                Pair("TimeTravel10", 16.8),
                Pair("BCD", 11.8),
                Pair("BeamHashII", 13.5)
            ), 0
        )


    )





    companion object {
        val instance = NvidiaDevices()

    }
}