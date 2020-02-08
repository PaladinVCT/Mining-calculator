package by.lebedev.domain.collections

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import java.util.*

class AmdDevices : VendorDevices {

    override var devicesCount: Int = 0


    override val list: ArrayList<Device> = arrayListOf(
        Amd(
            0, "RX 460", hashMapOf(
                Pair("Ethash", 0.0),
                Pair("Equihash(210,9)", 0.0),
                Pair("Zhash", 0.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 0.0),
                Pair("MTP", 0.0),
                Pair("X25X", 0.0),
                Pair("Lyra2REv3", 0.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 0.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckaroom29", 0.0),
                Pair("TimeTravel10", 0.0),
                Pair("BCD", 0.2),
                Pair("BeamHashII", 0.0)
            ), 400, 0
        ),
        Amd(
            1, "RX 470", hashMapOf(
                Pair("Ethash", 26.0),
                Pair("Equihash(210,9)", 80.0),
                Pair("Zhash", 18.0),
                Pair("cuckAToo31", 0.2),
                Pair("X16R", 6.0),
                Pair("MTP", 0.0),
                Pair("X25X", 0.7),
                Pair("Lyra2REv3", 32.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 680.0),
                Pair("Skunkhash", 15.0),
                Pair("Cuckaroom29", 0.0),
                Pair("TimeTravel10", 11.0),
                Pair("BCD", 8.2),
                Pair("BeamHashII", 12.5)
            ), 660, 0
        ),
        Amd(
            2, "RX 480", hashMapOf(
                Pair("Ethash", 29.5),
                Pair("Equihash(210,9)", 95.0),
                Pair("Zhash", 21.0),
                Pair("cuckAToo31", 0.5),
                Pair("X16R", 8.0),
                Pair("MTP", 0.6),
                Pair("X25X", 0.8),
                Pair("Lyra2REv3", 39.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 820.0),
                Pair("Skunkhash", 18.0),
                Pair("Cuckaroom29", 2.2),
                Pair("TimeTravel10", 13.5),
                Pair("BCD", 10.1),
                Pair("BeamHashII", 14.5)
            ), 830, 0
        ),
        Amd(
            3, "RX 550", hashMapOf(
                Pair("Ethash", 31.5),
                Pair("Equihash(210,9)", 80.0),
                Pair("Zhash", 22.5),
                Pair("cuckAToo31", 0.7),
                Pair("X16R", 10.0),
                Pair("MTP", 0.8),
                Pair("X25X", 0.9),
                Pair("Lyra2REv3", 41.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 850.0),
                Pair("Skunkhash", 20.0),
                Pair("Cuckaroom29", 2.5),
                Pair("TimeTravel10", 14.5),
                Pair("BCD", 11.5),
                Pair("BeamHashII", 15.5)
            ), 400, 0
        ),
        Amd(
            4, "RX 560", hashMapOf(
                Pair("Ethash", 32.0),
                Pair("Equihash(210,9)", 81.0),
                Pair("Zhash", 23.0),
                Pair("cuckAToo31", 0.7),
                Pair("X16R", 11.0),
                Pair("MTP", 0.9),
                Pair("X25X", 0.9),
                Pair("Lyra2REv3", 42.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 860.0),
                Pair("Skunkhash", 21.0),
                Pair("Cuckaroom29", 2.7),
                Pair("TimeTravel10", 15.0),
                Pair("BCD", 11.8),
                Pair("BeamHashII", 15.9)
            ), 420, 0
        ),
        Amd(
            5, "RX 570", hashMapOf(
                Pair("Ethash", 27.9),
                Pair("Equihash(210,9)", 85.0),
                Pair("Zhash", 19.0),
                Pair("cuckAToo31", 0.2),
                Pair("X16R", 6.5),
                Pair("MTP", 0.0),
                Pair("X25X", 0.7),
                Pair("Lyra2REv3", 33.5),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 700.0),
                Pair("Skunkhash", 16.0),
                Pair("Cuckaroom29", 0.0),
                Pair("TimeTravel10", 11.5),
                Pair("BCD", 8.6),
                Pair("BeamHashII", 13.0)
            ), 730, 0
        ),
        Amd(
            6, "RX 580", hashMapOf(
                Pair("Ethash", 30.2),
                Pair("Equihash(210,9)", 95.0),
                Pair("Zhash", 21.0),
                Pair("cuckAToo31", 0.5),
                Pair("X16R", 8.0),
                Pair("MTP", 0.6),
                Pair("X25X", 0.8),
                Pair("Lyra2REv3", 39.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 820.0),
                Pair("Skunkhash", 18.0),
                Pair("Cuckaroom29", 2.2),
                Pair("TimeTravel10", 13.5),
                Pair("BCD", 10.1),
                Pair("BeamHashII", 14.5)
            ), 830, 0
        ),
        Amd(
            7, "RX Frontier", hashMapOf(
                Pair("Ethash", 30.2),
                Pair("Equihash(210,9)", 95.0),
                Pair("Zhash", 21.0),
                Pair("cuckAToo31", 0.5),
                Pair("X16R", 8.0),
                Pair("MTP", 0.6),
                Pair("X25X", 0.8),
                Pair("Lyra2REv3", 39.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 820.0),
                Pair("Skunkhash", 18.0),
                Pair("Cuckaroom29", 2.2),
                Pair("TimeTravel10", 13.5),
                Pair("BCD", 10.1),
                Pair("BeamHashII", 14.5)
            ), 1720, 0
        ),
        Amd(
            8, "RX FuryX", hashMapOf(
                Pair("Ethash", 29.0),
                Pair("Equihash(210,9)", 140.0),
                Pair("Zhash", 32.0),
                Pair("cuckAToo31", 0.0),
                Pair("X16R", 0.0),
                Pair("MTP", 0.0),
                Pair("X25X", 1.1),
                Pair("Lyra2REv3", 0.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 1250.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckaroom29", 0.0),
                Pair("TimeTravel10", 0.0),
                Pair("BCD", 8.2),
                Pair("BeamHashII", 21.5)
            ), 850, 0
        ),
        Amd(
            9, "RX Vega 56", hashMapOf(
                Pair("Ethash", 36.5),
                Pair("Equihash(210,9)", 130.0),
                Pair("Zhash", 34.0),
                Pair("cuckAToo31", 0.9),
                Pair("X16R", 10.0),
                Pair("MTP", 0.0),
                Pair("X25X", 1.8),
                Pair("Lyra2REv3", 51.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 1600.0),
                Pair("Skunkhash", 36.0),
                Pair("Cuckaroom29", 3.9),
                Pair("TimeTravel10", 17.2),
                Pair("BCD", 11.9),
                Pair("BeamHashII", 17.0)
            ), 1720, 0
        ),
        Amd(
            10, "RX Vega 64", hashMapOf(
                Pair("Ethash", 40.0),
                Pair("Equihash(210,9)", 145.0),
                Pair("Zhash", 38.0),
                Pair("cuckAToo31", 1.1),
                Pair("X16R", 12.0),
                Pair("MTP", 0.0),
                Pair("X25X", 2.1),
                Pair("Lyra2REv3", 59.0),
                Pair("CuckooCycle", 0.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 2000.0),
                Pair("Skunkhash", 40.0),
                Pair("Cuckaroom29", 4.3),
                Pair("TimeTravel10", 20.5),
                Pair("BCD", 14.6),
                Pair("BeamHashII", 17.5)
            ), 1800, 0
        ),
        Amd(
            11, "RX Vega VII", hashMapOf(
                Pair("Ethash", 78.0),
                Pair("Equihash(210,9)", 160.0),
                Pair("Zhash", 49.0),
                Pair("cuckAToo31", 1.6),
                Pair("X16R", 0.0),
                Pair("MTP", 0.0),
                Pair("X25X", 2.5),
                Pair("Lyra2REv3", 90.0),
                Pair("CuckooCycle", 5.0),
                Pair("PHI2", 0.0),
                Pair("NeoScrypt", 2150.0),
                Pair("Skunkhash", 0.0),
                Pair("Cuckaroom29", 5.9),
                Pair("TimeTravel10", 30.0),
                Pair("BCD", 0.0),
                Pair("BeamHashII", 29.5)
            ), 2800, 0
        )
    )

    companion object {
        val instance = AmdDevices()

    }
}