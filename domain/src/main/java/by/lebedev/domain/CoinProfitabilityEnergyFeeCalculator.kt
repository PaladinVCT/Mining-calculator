package by.lebedev.domain

import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityEnergyFeeCalculator {


    fun execute(
        coinListProfitability: ArrayList<CoinProfitability>,
        energy: Double,
        energyCost: Double,
        fee: Double
    ): ArrayList<CoinProfitability> {

        for (i in 0 until coinListProfitability.size) {

            coinListProfitability.get(i).rewardDayUsd = coinListProfitability.get(i).rewardDayUsd.toDouble()


        }

        return
    }
}