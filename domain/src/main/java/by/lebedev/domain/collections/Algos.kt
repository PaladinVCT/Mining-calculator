package by.lebedev.domain.collections

import java.util.*

class Algos {

    var selectedAlgo = "Cryptonight"

    val gpuList: ArrayList<String> = arrayListOf(
        "Cryptonight",
        "Ethash",
        "KawPow",
        "ProgPowZ",
        "Equihash(210,9)",
        "Zhash",
        "cuckAToo31",
        "MTP",
        "Lyra2REv3",
        "CuckooCycle",
        "PHI2",
        "NeoScrypt",
        "Skunkhash",
        "Cuckarood29",
        "TimeTravel10",
        "BCD",
        "BeamHashII",
        "Equihash(192,7)",
        "Equihash(125,4)"
//        "RainForestV2",
//        "AstroBWT",
//        "Argon2",
//        "Argon2d",
//        "Tensority",
//        "vBlake2",
//        "Ubqhash",
//        "HoneyComb",
//        "X16R",
//        "X17",
//        "X21S",
//        "X25X",
//        "Odocrypt",
//        "Solidity-SHA3",
//        "Groestl",
//        "K12",
//        "Handshake",
//        "Kadena",
//        "Eaglesong",
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