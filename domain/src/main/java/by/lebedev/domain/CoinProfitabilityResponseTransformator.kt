package by.lebedev.domain

import by.lebedev.data.repository.entities.CoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability
import java.text.DecimalFormat

class CoinProfitabilityResponseTransformator {

    val nf2 = DecimalFormat("#.##")
    val nf5 = DecimalFormat("#.####")


    fun execute(coinListProfitabilityResponse: ArrayList<CoinProfitabilityResponse>): ArrayList<CoinProfitability> {

        val nf4 = DecimalFormat("#,###.####")
        val nf8 = DecimalFormat("#,###.######")
        val nfVolume = DecimalFormat("##############.##")
        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until coinListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.cryptunit.com/coinicons/${coinListProfitabilityResponse.get(i).coin_id}.png",
                    coinListProfitabilityResponse.get(i).coin_name,
                    coinListProfitabilityResponse.get(i).algo_id,
                    coinListProfitabilityResponse.get(i).algo_name,
                    coinListProfitabilityResponse.get(i).hashrate_auto.toString()+" H/s",
                    "Daily profit: \n"+nf4.format(coinListProfitabilityResponse.get(i).reward_day_usd)+" $",
                    "Monthly profit: \n"+nf4.format(coinListProfitabilityResponse.get(i).reward_month_usd)+" $",
                    "Daily coins: \n"+nf8.format(coinListProfitabilityResponse.get(i).reward_day_coins)+" "+coinListProfitabilityResponse.get(i).coin_ticker ,
                    "Monthly coins: \n"+nf8.format(coinListProfitabilityResponse.get(i).reward_month_coins)+" "+coinListProfitabilityResponse.get(i).coin_ticker ,
                    nfVolume.format(coinListProfitabilityResponse.get(i).volume_24h_usd)+" $"
                )
            )
        }
        return coinList
    }
}