package by.lebedev.domain.transformators

import by.lebedev.domain.collections.Algos
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityNvidiaRigTransformator {

    private val algos = Algos.instance.gpuList
    private val fullHashrateMap = aggregateHashrateNvidia()
    private val modifiedList = arrayListOf<CoinProfitability>()

    fun execute(
        list: ArrayList<CoinProfitability>,

        selectedHashrate: Double
    ): ArrayList<CoinProfitability> {


        for (i in 0 until list.size) {

            for (j in 0 until algos.size) {
                if (list[i].algoName == algos[j]) {

                    val coefficient = (fullHashrateMap[algos[j]])?.div(selectedHashrate)
                        ?.times(HashTypeConfigurator().getDigitsFromType(HashTypeConfigurator().getTypeFromName(algos.get(j))))
                    list[i].hashrateAuto = fullHashrateMap[algos[j]].toString()
                    if (coefficient != null) {
                        list[i].rewardDayUsd *= coefficient
                        list[i].rewardDayUsdActual = list[i].rewardDayUsd
                        list[i].rewardMonthUsd *= coefficient
                        list[i].rewardMonthUsdActual = list[i].rewardMonthUsd
                        list[i].rewardDayCoins *= coefficient
                        list[i].rewardMonthCoins *= coefficient

                        if (fullHashrateMap[algos[j]] !=0.0){
                        modifiedList.add(list[i])}
                    }
                }
            }
        }

        modifiedList.sortByDescending {
            it.rewardMonthUsd
        }

        return modifiedList
    }


    private fun aggregateHashrateNvidia(): HashMap<String, Double> {

        val summMap = hashMapOf<String, Double>()

        for (j in 0 until Algos.instance.gpuList.size) {
            var tempSumm = 0.0


            for (i in 0 until NvidiaDevices.instance.list.size) {
                if (NvidiaDevices.instance.list[i].algos[Algos.instance.gpuList.get(j)] != null) {
                    tempSumm += NvidiaDevices.instance.list[i].algos[Algos.instance.gpuList[j]]!! * NvidiaDevices.instance.list[i].count
                }
            }
            summMap[Algos.instance.gpuList[j]] = tempSumm
        }
        return summMap
    }
}