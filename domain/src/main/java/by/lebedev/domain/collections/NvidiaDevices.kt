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
        ),
        Nvidia(
            2, "GTX 1660", hashMapOf(
                Pair("Ethash", 20.5),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 34.8),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 15.7),
                Pair("MTP", 1.9),
                Pair("X25X", 3.1),
                Pair("Lyra2REv3", 42.0),
                Pair("CuckooCycle", 3.7),
                Pair("PHI2", 5.6),
                Pair("NeoScrypt", 550.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckarood29", 3.7),
                Pair("TimeTravel10", 26.0),
                Pair("BCD", 18.5),
                Pair("BeamHashII", 0.0)
            ), 0, 0
        ),
        Nvidia(
            3, "GTX 1660Ti", hashMapOf(
                Pair("Ethash", 25.7),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 35.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 15.9),
                Pair("MTP", 1.9),
                Pair("X25X", 3.3),
                Pair("Lyra2REv3", 4.5),
                Pair("CuckooCycle", 4.3),
                Pair("PHI2", 5.6),
                Pair("NeoScrypt", 1050.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckarood29", 4.3),
                Pair("TimeTravel10", 26.5),
                Pair("BCD", 18.9),
                Pair("BeamHashII", 0.0)
            ), 0, 0
        ),
        Nvidia(
            4, "GTX 1070", hashMapOf(
                Pair("Ethash", 30.0),
                Pair("Equihash(210,9)", 200.0),
                Pair("Zhash", 56.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 17.0),
                Pair("MTP", 1.8),
                Pair("X25X", 4.0),
                Pair("Lyra2REv3", 44.6),
                Pair("CuckooCycle", 4.7),
                Pair("PHI2", 5.7),
                Pair("NeoScrypt", 1150.0),
                Pair("Skunkhash", 27.0),
                Pair("Cuckarood29", 4.0),
                Pair("TimeTravel10", 27.5),
                Pair("BCD", 19.9),
                Pair("BeamHashII", 24.0)
            ), 0, 0
        ),
        Nvidia(
            5, "GTX 1070Ti", hashMapOf(
                Pair("Ethash", 30.5),
                Pair("Equihash(210,9)", 205.0),
                Pair("Zhash", 59.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 18.0),
                Pair("MTP", 2.1),
                Pair("X25X", 4.6),
                Pair("Lyra2REv3", 51.5),
                Pair("CuckooCycle", 4.9),
                Pair("PHI2", 6.5),
                Pair("NeoScrypt", 1300.0),
                Pair("Skunkhash", 31.0),
                Pair("Cuckarood29", 4.5),
                Pair("TimeTravel10", 31.0),
                Pair("BCD", 22.5),
                Pair("BeamHashII", 24.5)
            ), 0, 0
        ),
        Nvidia(
            6, "GTX 1080", hashMapOf(
                Pair("Ethash", 34.0),
                Pair("Equihash(210,9)", 230.0),
                Pair("Zhash", 67.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 23.0),
                Pair("MTP", 2.1),
                Pair("X25X", 5.3),
                Pair("Lyra2REv3", 58.5),
                Pair("CuckooCycle", 5.4),
                Pair("PHI2", 6.9),
                Pair("NeoScrypt", 1500.0),
                Pair("Skunkhash", 37.0),
                Pair("Cuckarood29", 4.8),
                Pair("TimeTravel10", 37.0),
                Pair("BCD", 26.0),
                Pair("BeamHashII", 28.5)
            ), 0, 0
        ),
        Nvidia(
            7, "GTX 1080Ti", hashMapOf(
                Pair("Ethash", 49.5),
                Pair("Equihash(210,9)", 300.0),
                Pair("Zhash", 86.0),
                Pair("cuckAToo31", 1.4),
                Pair("X16R", 29.0),
                Pair("MTP", 1.9),
                Pair("X25X", 6.9),
                Pair("Lyra2REv3", 77.0),
                Pair("CuckooCycle", 7.4),
                Pair("PHI2", 9.2),
                Pair("NeoScrypt", 1900.0),
                Pair("Skunkhash", 50.0),
                Pair("Cuckarood29", 6.3),
                Pair("TimeTravel10", 49.5),
                Pair("BCD", 35.0),
                Pair("BeamHashII", 38.0)
            ), 0, 0
        ),
        Nvidia(
            8, "GTX 2060", hashMapOf(
                Pair("Ethash", 27.6),
                Pair("Equihash(210,9)", 220.0),
                Pair("Zhash", 54.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 21.0),
                Pair("MTP", 2.1),
                Pair("X25X", 4.2),
                Pair("Lyra2REv3", 54.0),
                Pair("CuckooCycle", 5.2),
                Pair("PHI2", 7.5),
                Pair("NeoScrypt", 1300.0),
                Pair("Skunkhash", 50.0),
                Pair("Cuckarood29", 4.5),
                Pair("TimeTravel10", 34.0),
                Pair("BCD", 24.2),
                Pair("BeamHashII", 25.0)
            ), 0, 0
        ),
        Nvidia(
            9, "GTX 2070", hashMapOf(
                Pair("Ethash", 36.9),
                Pair("Equihash(210,9)", 250.0),
                Pair("Zhash", 60.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 23.5),
                Pair("MTP", 2.6),
                Pair("X25X", 4.8),
                Pair("Lyra2REv3", 60.5),
                Pair("CuckooCycle", 6.7),
                Pair("PHI2", 8.6),
                Pair("NeoScrypt", 1700.0),
                Pair("Skunkhash", 55.0),
                Pair("Cuckarood29", 6.0),
                Pair("TimeTravel10", 38.5),
                Pair("BCD", 27.5),
                Pair("BeamHashII", 29.5)
            ), 0, 0
        ),
        Nvidia(
            10, "GTX 2080", hashMapOf(
                Pair("Ethash", 36.9),
                Pair("Equihash(210,9)", 335.0),
                Pair("Zhash", 82.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 31.0),
                Pair("MTP", 3.5),
                Pair("X25X", 6.3),
                Pair("Lyra2REv3", 81.0),
                Pair("CuckooCycle", 8.4),
                Pair("PHI2", 11.4),
                Pair("NeoScrypt", 2350.0),
                Pair("Skunkhash", 50.0),
                Pair("Cuckarood29", 7.6),
                Pair("TimeTravel10", 50.0),
                Pair("BCD", 37.0),
                Pair("BeamHashII", 40.5)
            ), 0, 0
        ),
        Nvidia(
            11, "GTX 2080Ti", hashMapOf(
                Pair("Ethash", 52.5),
                Pair("Equihash(210,9)", 375.0),
                Pair("Zhash", 96.0),
                Pair("cuckAToo31", 2.0),
                Pair("X16R", 38.0),
                Pair("MTP", 4.3),
                Pair("X25X", 7.9),
                Pair("Lyra2REv3", 98.5),
                Pair("CuckooCycle", 10.1),
                Pair("PHI2", 14.7),
                Pair("NeoScrypt", 2800.0),
                Pair("Skunkhash", 50.0),
                Pair("Cuckarood29", 9.5),
                Pair("TimeTravel10", 60.0),
                Pair("BCD", 45.55),
                Pair("BeamHashII", 48.2)
            ), 0, 0
        ),
        Nvidia(
            12, "GTX Titan X", hashMapOf(
                Pair("Ethash", 74.4),
                Pair("Equihash(210,9)", 425.5),
                Pair("Zhash", 107.4),
                Pair("cuckAToo31", 1.8),
                Pair("X16R", 48.9),
                Pair("MTP", 0.0),
                Pair("X25X", 9.6),
                Pair("Lyra2REv3", 106.8),
                Pair("CuckooCycle", 10.0),
                Pair("PHI2", 17.7),
                Pair("NeoScrypt", 2490.0),
                Pair("Skunkhash", 110.8),
                Pair("Cuckarood29", 0.0),
                Pair("TimeTravel10", 76.0),
                Pair("BCD", 49.3),
                Pair("BeamHashII", 0.0)
            ), 0, 0
        ),
        Nvidia(
            13, "P102-100", hashMapOf(
                Pair("Ethash", 48.9),
                Pair("Equihash(210,9)", 290.5),
                Pair("Zhash", 69.9),
                Pair("cuckAToo31", 1.37),
                Pair("X16R", 35.0),
                Pair("MTP", 0.0),
                Pair("X25X", 5.57),
                Pair("Lyra2REv3", 48.8),
                Pair("CuckooCycle", 5.17),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 0.0),
                Pair("Skunkhash", 58.8),
                Pair("Cuckarood29", 6.38),
                Pair("TimeTravel10", 32.82),
                Pair("BCD", 32.58),
                Pair("BeamHashII", 43.04)
            ), 904, 0
        ),
        Nvidia(
            14, "P104-100", hashMapOf(
                Pair("Ethash", 36.46),
                Pair("Equihash(210,9)", 209.25),
                Pair("Zhash", 49.4),
                Pair("cuckAToo31", 0.8),
                Pair("X16R", 20.91),
                Pair("MTP", 0.0),
                Pair("X25X", 3.96),
                Pair("Lyra2REv3", 34.03),
                Pair("CuckooCycle", 4.94),
                Pair("PHI2", 6.21),
                Pair("NeoScrypt", 810.0),
                Pair("Skunkhash", 38.26),
                Pair("Cuckarood29", 3.45),
                Pair("TimeTravel10", 25.15),
                Pair("BCD", 20.05),
                Pair("BeamHashII", 33.32)
            ), 694, 0
        ),
        Nvidia(
            15, "P106-100", hashMapOf(
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
            ), 370, 0
        ),
        Nvidia(
            16, "P100 Tesla", hashMapOf(
                Pair("Ethash", 44.85),
                Pair("Equihash(210,9)", 293.25),
                Pair("Zhash", 55.25),
                Pair("cuckAToo31", 0.66),
                Pair("X16R", 32.38),
                Pair("MTP", 0.0),
                Pair("X25X", 5.91),
                Pair("Lyra2REv3", 67.39),
                Pair("CuckooCycle", 5.0),
                Pair("PHI2", 8.92),
                Pair("NeoScrypt", 1250.0),
                Pair("Skunkhash", 63.29),
                Pair("Cuckarood29", 3.64),
                Pair("TimeTravel10", 37.48),
                Pair("BCD", 29.39),
                Pair("BeamHashII", 17.03)
            ), 1630, 0
        ),
        Nvidia(
            17, "RTX 3060Ti", hashMapOf(
                Pair("Ethash", 58.10),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 0.0),
                Pair("cuckAToo31", 0.55),
                Pair("X16R", 0.0),
                Pair("MTP", 3.9),
                Pair("X25X", 0.0),
                Pair("Lyra2REv3", 0.0),
                Pair("CuckooCycle", 9.8),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 0.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckarood29", 9.7),
                Pair("TimeTravel10", 0.0),
                Pair("BCD", 0.0),
                Pair("BeamHashII", 32.5)
            ), 2850, 0
        ),
        Nvidia(
            18, "RTX 3070", hashMapOf(
                Pair("Ethash", 58.10),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 100.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 0.0),
                Pair("MTP", 4.0),
                Pair("X25X", 8.0),
                Pair("Lyra2REv3", 0.0),
                Pair("CuckooCycle", 10.2),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 0.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckarood29", 5.8),
                Pair("TimeTravel10", 0.0),
                Pair("BCD", 0.0),
                Pair("BeamHashII", 34.0)
            ), 2900, 0
        ),
        Nvidia(
            19, "RTX 3080", hashMapOf(
                Pair("Ethash", 89.5),
                Pair("Equihash(210,9)", 470.0),
                Pair("Zhash", 130.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 38.0),
                Pair("MTP", 4.9),
                Pair("X25X", 0.0),
                Pair("Lyra2REv3", 0.0),
                Pair("CuckooCycle", 14.5),
                Pair("PHI2", 14.7),
                Pair("NeoScrypt", 0.0),
                Pair("Skunkhash", 50.0),
                Pair("Cuckarood29", 14.5),
                Pair("TimeTravel10", 60.0),
                Pair("BCD", 45.5),
                Pair("BeamHashII", 43.0)
            ), 3700, 0
        )
    )

    companion object {
        val instance = NvidiaDevices()

    }
}