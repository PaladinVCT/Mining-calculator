package by.lebedev.domain

class HashTypeConfigurator {


    fun get(algo: String): String {
        when (algo) {
            "Cryptonight" -> {
                return "H/s"
            }
            "Ethash" -> {
                return "Mh/s"
            }
            "Equihash(150,5)" -> {
                return "H/s"
            }
            "Equihash(210,9)" -> {
                return "H/s"
            }
            "Zhash" -> {
                return "H/s"
            }
            "cuckAToo31" -> {
                return "H/s"
            }
            "X16R" -> {
                return "Mh/s"
            }
            "MTP" -> {
                return "Mh/s"
            }
            "X25X" -> {
                return "Mh/s"
            }
            "Lyra2REv3" -> {
                return "Mh/s"
            }
            "X16S" -> {
                return "Mh/s"
            }
            "CuckooCycle" -> {
                return "H/s"
            }
            "PHI2" -> {
                return "Mh/s"
            }
            "NeoScrypt" -> {
                return "Kh/s"
            }
            "Skunkhash" -> {
                return "Mh/s"
            }
            "Cuckarood29" -> {
                return "H/s"
            }
            "ProgPow" -> {
                return "Mh/s"
            }
            "TimeTravel10" -> {
                return "Mh/s"
            }
            else -> return "H/s"
        }

    }
}