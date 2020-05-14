package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.coincap.CoinCapDataResponse
import by.lebedev.data.repository.entities.history.RateHistoryDataResponse
import by.lebedev.domain.R
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.entities.Data
import by.lebedev.domain.entities.RateHistoryData
import java.text.NumberFormat
import kotlin.math.roundToInt

class RateHistoryDataResponseTransformator {

    private val rateHistoryList: ArrayList<Data> = ArrayList()

    fun execute(rateHistoryDataResponse: RateHistoryDataResponse): ArrayList<Data> {

        for (i in rateHistoryDataResponse.data.indices) {

            rateHistoryList.add(
                Data(
                    rateHistoryDataResponse.data[i].priceUsd,
                    rateHistoryDataResponse.data[i].time
                )
            )
        }
        return rateHistoryList
    }
}