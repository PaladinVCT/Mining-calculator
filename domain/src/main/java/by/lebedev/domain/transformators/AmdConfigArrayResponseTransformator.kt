package by.lebedev.domain.transformators

import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.entities.Config

class AmdConfigArrayResponseTransformator {

    fun execute(configArrayResponse: ArrayList<ConfigResponse>): ArrayList<Config> {

        val configArray = arrayListOf<Config>()
        for (i in 0 until configArrayResponse.size) {

            if (configArrayResponse.get(i).vendor.equals("Amd")) {
                configArray.add(Config(configArrayResponse.get(i).name, configArrayResponse.get(i).numberDevices))
            }
        }

        return configArray
    }
}