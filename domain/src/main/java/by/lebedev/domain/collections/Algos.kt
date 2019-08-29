package by.lebedev.domain.collections

import java.util.*

class Algos {

    var selectedAlgo = "Cryptonight"

    val list: ArrayList<String> = arrayListOf("Cryptonight", "Ethash","Equihash(150,5)","Equihash(210,9)",
        "Zhash","cuckAToo31","X16R","MTP","X25X","X16R","Lyra2REv3","X16S","CuckooCycle","PHI2","NeoScrypt","Skunkhash",
        "Cuckarood29","ProgPow","TimeTravel10")



    companion object {
        val instance = Algos()
    }
}