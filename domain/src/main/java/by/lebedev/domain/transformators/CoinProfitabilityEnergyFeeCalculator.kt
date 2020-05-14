package by.lebedev.domain.transformators

import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityEnergyFeeCalculator {


    fun execute(
        coinListProfitability: ArrayList<CoinProfitability>,
        energy: Double,
        energyCost: Double,
        fee: Double
    ): ArrayList<CoinProfitability> {

        for (i in 0 until coinListProfitability.size) {
            //minus Fee
            if (fee > 0) {
                coinListProfitability[i].rewardDayUsdActual -= coinListProfitability[i].rewardDayUsdActual * fee.div(100)
                coinListProfitability[i]
                    .rewardMonthUsdActual -= coinListProfitability[i].rewardMonthUsdActual * fee.div(100)
                coinListProfitability[i]
                    .rewardDayCoins -= coinListProfitability[i].rewardDayCoins * fee.div(100)
                coinListProfitability[i]
                    .rewardMonthCoins -= coinListProfitability[i].rewardMonthCoins * fee.div(100)
            }
            //minus energy cost
            if (energyCost > 0 && energy > 0) {
                coinListProfitability[i].rewardDayUsdActual -= energyCost.div(1000) * energy * 24
                coinListProfitability[i].rewardMonthUsdActual -= energyCost.div(1000) * energy * 24 * 30
            }
        }

        return coinListProfitability
    }
}