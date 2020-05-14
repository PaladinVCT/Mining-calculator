package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.coincap.CoinCapDataResponse
import by.lebedev.data.repository.entities.coindetails.CoinDetailsResponse
import by.lebedev.data.repository.entities.history.RateHistoryDataResponse
import by.lebedev.domain.R
import by.lebedev.domain.entities.*
import java.text.NumberFormat
import kotlin.math.roundToInt

class CoinDetailsResponseTransformator {

    private var detailsList: Details? = null

    fun execute(coinDetailsResponse: CoinDetailsResponse): Details {

        return Details(
            coinDetailsResponse.data.changePercent24Hr,
            coinDetailsResponse.data.id,
            coinDetailsResponse.data.marketCapUsd,
            coinDetailsResponse.data.maxSupply,
            coinDetailsResponse.data.name,
            coinDetailsResponse.data.priceUsd,
            coinDetailsResponse.data.rank,
            coinDetailsResponse.data.maxSupply,
            coinDetailsResponse.data.symbol,
            coinDetailsResponse.data.volumeUsd24Hr,
            coinDetailsResponse.data.vwap24Hr
        )
    }
}