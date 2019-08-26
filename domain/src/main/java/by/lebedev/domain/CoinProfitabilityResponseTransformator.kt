package by.lebedev.domain

import by.lebedev.data.repository.entities.CoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityResponseTransformator {

    fun execute(coinListProfitabilityResponse: ArrayList<CoinProfitabilityResponse>): ArrayList<CoinProfitability> {

        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until coinListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.cryptunit.com/coinicons/${coinListProfitabilityResponse.get(i).coin_id}.png",
                    coinListProfitabilityResponse.get(i).coin_name,
                    coinListProfitabilityResponse.get(i).algo_id,
                    coinListProfitabilityResponse.get(i).algo_name,
                    coinListProfitabilityResponse.get(i).hashrate_auto,
                    coinListProfitabilityResponse.get(i).reward_day_usd,
                    coinListProfitabilityResponse.get(i).reward_month_usd,
                    coinListProfitabilityResponse.get(i).reward_day_coins,
                    coinListProfitabilityResponse.get(i).reward_month_coins,
                    coinListProfitabilityResponse.get(i).volume_24h_usd
                )
            )
        }
        return coinList
    }
}