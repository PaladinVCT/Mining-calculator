package by.lebedev.domain.transformators

import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.entities.Config

class NvidiaConfigArrayResponseTransformator {

    private val vendor = "Nvidia"

    fun execute(configArrayResponse: List<ConfigResponse>): ArrayList<Config> {

        val configArray = arrayListOf<Config>()
        for (i in configArrayResponse.indices) {

            if (configArrayResponse[i].vendor == vendor) {
                configArray.add(Config(configArrayResponse[i].name, configArrayResponse[i].numberDevices))
            }
        }
        return configArray
    }
}