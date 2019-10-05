package by.lebedev.domain.transformators

class HashTypeConfigurator {


    fun getTypeFromName(algo: String): String {
        when (algo) {
            "Cryptonight" -> {
                return "H/s"
            }
            "Ethash" -> {
                return "Mh/s"
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
            "BeamHashII" -> {
                return "H/s"
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
            "BCD" -> {
                return "Mh/s"
            }
            "SHA-256" -> {
                return "Gh/s"
            }
            "Scrypt" -> {
                return "Mh/s"
            }
            "X11" -> {
                return "Gh/s"
            }
            "Quark" -> {
                return "Mh/s"
            }
            "Qubit" -> {
                return "Mh/s"
            }
            "Blake (2b)" -> {
                return "Th/s"
            }
            "LBRY" -> {
                return "Gh/s"
            }
            "Blake (14r)" -> {
                return "Gh/s"
            }
            "CryptoNight" -> {
                return "Kh/s"
            }
            "Equihash" -> {
                return "Kh/s"
            }
            "Skein" -> {
                return "Gh/s"
            }
            "Myr-Groestl" -> {
                return "Gh/s"
            }
            "Lyra2REv2" -> {
                return "Gh/s"
            }
            "Keccak" -> {
                return "Gh/s"
            }
            "PHI1612" -> {
                return "Mh/s"
            }
            "Lyra2z" -> {
                return "Mh/s"
            }
            "X13" -> {
                return "Mh/s"
            }
            "Blake (2b-Sia)" -> {
                return "Gh/s"
            }
            else -> return "H/s"
        }

    }


    fun getDigitsFromType(algoType: String): Long {
        when (algoType) {
            "H/s" -> {
                return 1
            }
            "Kh/s" -> {
                return 1000
            }
            "Mh/s" -> {
                return 1000000
            }
            "Gh/s" -> {
                return 1000000000
            }
            "Th/s" -> {
                return 1000000000000
            }
            else -> return 1
        }

    }


}