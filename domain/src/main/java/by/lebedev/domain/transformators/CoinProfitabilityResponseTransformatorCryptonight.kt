package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.CryptonightCoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityResponseTransformatorCryptonight {


    fun execute(cryptonightCoinListProfitabilityResponse: ArrayList<CryptonightCoinProfitabilityResponse>): ArrayList<CoinProfitability> {

        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until cryptonightCoinListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.cryptunit.com/coinicons/${cryptonightCoinListProfitabilityResponse[i].coin_id}.png",
                    cryptonightCoinListProfitabilityResponse[i].coin_name,
                    cryptonightCoinListProfitabilityResponse[i].coin_ticker,
                    cryptonightCoinListProfitabilityResponse[i].algo_id,
                    cryptonightCoinListProfitabilityResponse[i].algo_name,
                    cryptonightCoinListProfitabilityResponse[i].hashrate_auto.toString(),
                    cryptonightCoinListProfitabilityResponse[i].reward_day_usd,
                    cryptonightCoinListProfitabilityResponse[i].reward_day_usd,
                    cryptonightCoinListProfitabilityResponse[i].reward_month_usd,
                    cryptonightCoinListProfitabilityResponse[i].reward_month_usd,
                    cryptonightCoinListProfitabilityResponse[i].reward_day_coins,
                    cryptonightCoinListProfitabilityResponse[i].reward_month_coins,
                    cryptonightCoinListProfitabilityResponse[i].volume_24h_usd
                )
            )
        }
        return coinList
    }
}