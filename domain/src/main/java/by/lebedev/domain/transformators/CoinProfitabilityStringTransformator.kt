package by.lebedev.domain.transformators

import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.entities.CoinProfitabilityString
import java.text.DecimalFormat

class CoinProfitabilityStringTransformator {

    private val nfUsd = DecimalFormat("#,###.#####")
    private val nfCoin = DecimalFormat("#,###.######")
    private val nfVolume = DecimalFormat("##############.##")
    private var showAlert: Boolean = false

    fun execute(coinProfitability: ArrayList<CoinProfitability>): ArrayList<CoinProfitabilityString> {

        val coinList: ArrayList<CoinProfitabilityString> = ArrayList()

        for (i in 0 until coinProfitability.size) {

            showAlert = coinProfitability[i].volumeUsd < 150000

            coinList.add(
                i, CoinProfitabilityString(
                    coinProfitability[i].imageUrl,
                    coinProfitability[i].coinName,
                    coinProfitability[i].coinTicker,
                    coinProfitability[i].algoId,
                    coinProfitability[i].algoName,
                    coinProfitability[i].hashrateAuto + " " + HashTypeConfigurator().getTypeFromName(
                        coinProfitability[i].algoName
                    ),
                    "Daily profit: \n" + nfUsd.format(coinProfitability[i].rewardDayUsd) + " $" +
                            " \n(" + nfUsd.format(coinProfitability[i].rewardDayUsdActual) + ")$",
                    "Monthly profit: \n" + nfUsd.format(coinProfitability[i].rewardMonthUsd) + " $" +
                            " \n(" + nfUsd.format(coinProfitability[i].rewardMonthUsdActual) + ")$",
                    "Daily coins: \n" + nfCoin.format(coinProfitability[i].rewardDayCoins) + " " + coinProfitability[i].coinTicker,
                    "Monthly coins: \n" + nfCoin.format(coinProfitability.get(i).rewardMonthCoins) + " " + coinProfitability[i].coinTicker,
                    nfVolume.format(coinProfitability[i].volumeUsd) + " $",
                    showAlert
                )
            )
        }
        return coinList
    }
}