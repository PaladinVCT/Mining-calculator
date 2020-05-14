package by.lebedev.domain.transformators

import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.entities.Config

class AmdConfigArrayResponseTransformator {

    private val vendor = "Amd"

    fun execute(configArrayResponse: List<ConfigResponse>): ArrayList<Config> {

        val configArray = arrayListOf<Config>()
        for (i in configArrayResponse.indices) {

            if (configArrayResponse[i].vendor.equals(vendor)) {
                configArray.add(Config(configArrayResponse[i].name, configArrayResponse[i].numberDevices))
            }
        }
        return configArray
    }
}