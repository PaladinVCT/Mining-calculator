package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.coingecko.CoinGeckoResponse
import by.lebedev.domain.entities.CoinGeckoCoin

class CoinGeckoResponseToCoinGeckoTransformator {

    fun execute(coinGeckoResponse: CoinGeckoResponse): ArrayList<CoinGeckoCoin> {

        val coinGeckoArray = arrayListOf<CoinGeckoCoin>()
        for (i in 0 until coinGeckoResponse.size) {
            coinGeckoArray.add(
                CoinGeckoCoin(
                    coinGeckoResponse[i].id,
                    coinGeckoResponse[i].name,
                    coinGeckoResponse[i].symbol
                )
            )
        }

        return coinGeckoArray
    }
}