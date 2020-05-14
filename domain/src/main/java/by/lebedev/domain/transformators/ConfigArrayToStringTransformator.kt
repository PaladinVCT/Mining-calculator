package by.lebedev.domain.transformators

import by.lebedev.domain.entities.Config

class ConfigArrayToStringTransformator {

    fun execute(configArray: ArrayList<Config>): Array<String> {

        val namesArray = arrayListOf<String>()
        for (i in 0 until configArray.size) {
            namesArray.add(configArray[i].name)
        }

        return namesArray.toTypedArray()
    }
}