package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.CryptonightCoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityResponseTransformatorCryptonight {


    fun execute(cryptonightCoinListProfitabilityResponse: ArrayList<CryptonightCoinProfitabilityResponse>): ArrayList<CoinProfitability> {

        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until cryptonightCoinListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.cryptunit.com/coinicons/${cryptonightCoinListProfitabilityResponse.get(i).coin_id}.png",
                    cryptonightCoinListProfitabilityResponse.get(i).coin_name,
                    cryptonightCoinListProfitabilityResponse.get(i).coin_ticker,
                    cryptonightCoinListProfitabilityResponse.get(i).algo_id,
                    cryptonightCoinListProfitabilityResponse.get(i).algo_name,
                    cryptonightCoinListProfitabilityResponse.get(i).hashrate_auto.toString(),
                    cryptonightCoinListProfitabilityResponse.get(i).reward_day_usd,
                    cryptonightCoinListProfitabilityResponse.get(i).reward_day_usd,
                    cryptonightCoinListProfitabilityResponse.get(i).reward_month_usd,
                    cryptonightCoinListProfitabilityResponse.get(i).reward_month_usd,
                    cryptonightCoinListProfitabilityResponse.get(i).reward_day_coins,
                    cryptonightCoinListProfitabilityResponse.get(i).reward_month_coins,
                    cryptonightCoinListProfitabilityResponse.get(i).volume_24h_usd
                )
            )
        }
        return coinList
    }
}