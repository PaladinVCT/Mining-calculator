package by.lebedev.domain.transformators

import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.entities.Config

class NvidiaConfigArrayResponseTransformator {

    private val vendor = "Nvidia"

    fun execute(configArrayResponse: List<ConfigResponse>): ArrayList<Config> {

        val configArray = arrayListOf<Config>()
        for (i in 0 until configArrayResponse.size) {

            if (configArrayResponse.get(i).vendor.equals(vendor)) {
                configArray.add(Config(configArrayResponse.get(i).name, configArrayResponse.get(i).numberDevices))
            }
        }

        return configArray
    }
}