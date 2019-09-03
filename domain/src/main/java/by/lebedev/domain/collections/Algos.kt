package by.lebedev.domain.collections

import java.util.*

class Algos {

    var selectedAlgo = "Cryptonight"

    val list: ArrayList<String> = arrayListOf(
        "Cryptonight",
        "Ethash",
        "Equihash(210,9)",
        "Zhash",
        "cuckAToo31",
        "X16R",
        "MTP",
        "X25X",
        "Lyra2REv3",
        "CuckooCycle",
        "PHI2",
        "NeoScrypt",
        "Skunkhash",
        "Cuckarood29",
        "TimeTravel10",
        "BCD",
        "BeamHashII"
    )


    companion object {
        val instance = Algos()
    }
}