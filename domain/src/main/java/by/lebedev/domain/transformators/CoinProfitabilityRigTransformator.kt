package by.lebedev.domain.transformators

import by.lebedev.domain.collections.Algos
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityRigTransformator {

    private val algos = Algos.instance.list
    private val fullHashrateMap = aggregateHashrateNvidia()

    fun execute(
        list: ArrayList<CoinProfitability>,
        selectedHashrate: Double
    ): ArrayList<CoinProfitability> {

        for (i in 0 until list.size) {

            for (j in 0 until algos.size) {
                if (list.get(i).algoName.equals(algos.get(j))) {

                    val coefficient = (fullHashrateMap.get(algos.get(j)))?.times(100.0)?.div(selectedHashrate)

                    list.get(i).hashrateAuto = fullHashrateMap.get(algos.get(j)).toString()
                    if (coefficient != null) {
                        list.get(i).rewardDayUsd *= coefficient
                        list.get(i).rewardDayUsdActual = list.get(i).rewardDayUsd
                        list.get(i).rewardMonthUsd *= coefficient
                        list.get(i).rewardMonthUsdActual = list.get(i).rewardMonthUsd
                        list.get(i).rewardDayCoins *= coefficient
                        list.get(i).rewardMonthCoins *= coefficient
                    }

                }

            }

        }

        return list
    }


   private fun aggregateHashrateNvidia(): HashMap<String, Double> {

        val summMap = hashMapOf<String, Double>()

        for (j in 0 until Algos.instance.list.size) {
            var tempSumm = 0.0


            for (i in 0 until NvidiaDevices.instance.list.size) {
                if (NvidiaDevices.instance.list.get(i).algos.get(Algos.instance.list.get(j)) != null) {
                    tempSumm += NvidiaDevices.instance.list.get(i).algos.get(Algos.instance.list.get(j))!! * NvidiaDevices.instance.list.get(
                        i
                    ).count
                }
            }

            summMap.put(Algos.instance.list.get(j), tempSumm)

        }

        return summMap
    }

}