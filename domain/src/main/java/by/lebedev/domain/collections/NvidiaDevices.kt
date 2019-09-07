package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import by.lebedev.domain.entities.Nvidia
import java.util.*
import kotlin.collections.HashMap

class NvidiaDevices : VendorDevices {

    override var devicesCount: Int = 0


    override val list: ArrayList<Device> = arrayListOf(
        Nvidia(
            0, "GTX 1050Ti", hashMapOf(
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
            ), 0, 0
        ),
        Nvidia(
            1, "GTX 1060", hashMapOf(
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
            ), 0, 0
        )
        ,
        Nvidia(
            2, "GTX 1660", hashMapOf(
                Pair("Ethash", 20.5),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 34.8),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 15.7),
                Pair("MTP",1.9),
                Pair("X25X",3.1),
                Pair("Lyra2REv3",42.0),
                Pair("CuckooCycle",3.7),
                Pair("PHI2",5.6),
                Pair("NeoScrypt",550.0),
                Pair("Skunkhash",0.0),
                Pair("Cuckarood29",3.7),
                Pair("TimeTravel10",26.0),
                Pair("BCD",18.5),
                Pair("BeamHashII",0.0)
            ), 0, 0
        )
        ,
        Nvidia(
            3, "GTX 1660Ti", hashMapOf(
                Pair("Ethash",25.7),
                Pair("Equihash(210,9)",0.0),
                Pair("Zhash",35.0),
                Pair("cuckAToo31",0.0),
                Pair("X16R",15.9),
                Pair("MTP",1.9),
                Pair("X25X",3.3),
                Pair("Lyra2REv3",4.5),
                Pair("CuckooCycle",4.3),
                Pair("PHI2",5.6),
                Pair("NeoScrypt",1050.0),
                Pair("Skunkhash",0.0),
                Pair("Cuckarood29",4.3),
                Pair("TimeTravel10",26.5),
                Pair("BCD",18.9),
                Pair("BeamHashII",0.0)
            ), 0, 0
        )
        ,
        Nvidia(
            4, "GTX 1070", hashMapOf(
                Pair("Ethash",30.0),
                Pair("Equihash(210,9)",200.0),
                Pair("Zhash",56.0),
                Pair("cuckAToo31",0.0),
                Pair("X16R",17.0),
                Pair("MTP",1.8),
                Pair("X25X",4.0),
                Pair("Lyra2REv3",44.6),
                Pair("CuckooCycle",4.7),
                Pair("PHI2",5.7),
                Pair("NeoScrypt",1150.0),
                Pair("Skunkhash",27.0),
                Pair("Cuckarood29",4.0),
                Pair("TimeTravel10",27.5),
                Pair("BCD",19.9),
                Pair("BeamHashII",24.0)
            ), 0, 0
        )
        ,
        Nvidia(
            5, "GTX 1070Ti", hashMapOf(
                Pair("Ethash",30.5),
                Pair("Equihash(210,9)",205.0),
                Pair("Zhash",59.0),
                Pair("cuckAToo31",0.0),
                Pair("X16R",18.0),
                Pair("MTP",2.1),
                Pair("X25X",4.6),
                Pair("Lyra2REv3",51.5),
                Pair("CuckooCycle",4.9),
                Pair("PHI2",6.5),
                Pair("NeoScrypt",1300.0),
                Pair("Skunkhash",31.0),
                Pair("Cuckarood29",4.5),
                Pair("TimeTravel10",31.0),
                Pair("BCD",22.5),
                Pair("BeamHashII",24.5)
            ), 0, 0
        )
        ,
        Nvidia(
            6, "GTX 1080", hashMapOf(
                Pair("Ethash",34.0),
                Pair("Equihash(210,9)",230.0),
                Pair("Zhash",67.0),
                Pair("cuckAToo31",0.0),
                Pair("X16R",23.0),
                Pair("MTP",2.1),
                Pair("X25X",5.3),
                Pair("Lyra2REv3",58.5),
                Pair("CuckooCycle",5.4),
                Pair("PHI2",6.9),
                Pair("NeoScrypt",1500.0),
                Pair("Skunkhash",37.0),
                Pair("Cuckarood29",4.8),
                Pair("TimeTravel10",37.0),
                Pair("BCD",26.0),
                Pair("BeamHashII",28.5)
            ), 0, 0
        )

    )


    companion object {
        val instance = NvidiaDevices()

    }
}