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

    val asicList: ArrayList<String> = arrayListOf(
        "SHA-256",
        "Scrypt",
        "X11",
        "Quark",
        "Qubit",
        "Blake (2b)",
        "LBRY",
        "Blake (14r)",
        "CryptoNight",
        "Equihash",
        "Skein",
        "Myr-Groestl",
        "Lyra2REv2",
        "Keccak",
        "PHI1612",
        "Lyra2z",
        "X13",
        "Blake (2b-Sia)"
    )


    companion object {
        val instance = Algos()
    }
}