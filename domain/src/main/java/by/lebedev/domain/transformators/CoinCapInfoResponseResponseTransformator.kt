package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.CryptonightCoinProfitabilityResponse
import by.lebedev.data.repository.entities.NvidiaCoinProfitabilityResponse
import by.lebedev.data.repository.entities.coincap.CoinCapInfoResponse
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.entities.CoinRate

class CoinCapInfoResponseResponseTransformator {

    private val coinRateList: ArrayList<CoinRate> = ArrayList()

    fun execute(coinCapInfoResponse: CoinCapInfoResponse): ArrayList<CoinRate> {

        for (i in 0 until coinCapInfoResponse.data.size) {

            coinRateList.add(
                CoinRate(
                    coinCapInfoResponse.data.get(i).id,
                    coinCapInfoResponse.data.get(i).name,
                    "https://s2.coinmarketcap.com/static/img/coins/32x32/${coinCapInfoResponse.data.get(i).id}.png",
                    coinCapInfoResponse.data.get(i).symbol,
                    Math.round(coinCapInfoResponse.data.get(i).quote.USD.price * 100.0) / 100.0,
                    Math.round(coinCapInfoResponse.data.get(i).quote.USD.percent_change_24h * 100.0) / 100.0
                )
            )
        }
        return coinRateList
    }
}