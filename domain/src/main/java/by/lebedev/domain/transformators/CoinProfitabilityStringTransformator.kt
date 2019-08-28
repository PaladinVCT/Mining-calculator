package by.lebedev.domain.transformators

import android.graphics.Color
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.entities.CoinProfitabilityString
import java.text.DecimalFormat

class CoinProfitabilityStringTransformator {

    val nf4 = DecimalFormat("#,###.####")
    val nf8 = DecimalFormat("#,###.######")
    val nfVolume = DecimalFormat("##############.##")

    fun execute(coinProfitability: ArrayList<CoinProfitability>): ArrayList<CoinProfitabilityString> {


        val coinList: ArrayList<CoinProfitabilityString> = ArrayList()

        for (i in 0 until coinProfitability.size) {

            coinList.add(
                i, CoinProfitabilityString(
                    coinProfitability.get(i).imageUrl,
                    coinProfitability.get(i).coinName,
                    coinProfitability.get(i).algoId,
                    coinProfitability.get(i).algoName,
                    coinProfitability.get(i).hashrateAuto+" "+HashTypeConfigurator().getTypeFromName(coinProfitability.get(i).algoName),
                    "Daily profit: \n"+nf4.format(coinProfitability.get(i).rewardDayUsd)+" $"+
                    " \n("+nf4.format(coinProfitability.get(i).rewardDayUsdActual)+")$",
                    "Monthly profit: \n"+nf4.format(coinProfitability.get(i).rewardMonthUsd)+" $"+
                     " \n("+nf4.format(coinProfitability.get(i).rewardMonthUsdActual)+")$",
                    "Daily coins: \n"+nf8.format(coinProfitability.get(i).rewardDayCoins)+" "+coinProfitability.get(i).coinTicker,
                    "Monthly coins: \n"+nf8.format(coinProfitability.get(i).rewardMonthCoins)+" "+coinProfitability.get(i).coinTicker,
                    nfVolume.format(coinProfitability.get(i).volumeUsd)+" $"
                )
            )
        }
        return coinList
    }
}