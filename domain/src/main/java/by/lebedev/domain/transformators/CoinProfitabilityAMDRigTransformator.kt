package by.lebedev.domain.transformators

import by.lebedev.domain.collections.Algos
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.NvidiaDevices
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityAMDRigTransformator {

    private val algos = Algos.instance.gpuList
    private val fullHashrateMap = aggregateHashrateAMDRigNvidiaAlgos()
    private val modifiedList = arrayListOf<CoinProfitability>()

    fun execute(
        list: ArrayList<CoinProfitability>,
        selectedHashrate: Double
    ): ArrayList<CoinProfitability> {


        for (i in 0 until list.size) {

            for (j in 0 until algos.size) {
                if (list[i].algoName == algos[j]) {

                    val coefficient = (fullHashrateMap[algos[j]])?.div(selectedHashrate)
                        ?.times(HashTypeConfigurator().getDigitsFromType(HashTypeConfigurator().getTypeFromName(
                            algos[j]
                        )))
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

    private fun aggregateHashrateAMDRigNvidiaAlgos(): HashMap<String, Double> {

        val summMap = hashMapOf<String, Double>()

        for (j in 0 until Algos.instance.gpuList.size) {
            var tempSumm = 0.0


            for (i in 0 until AmdDevices.instance.list.size) {
                if (AmdDevices.instance.list[i].algos[Algos.instance.gpuList[j]] != null) {
                    tempSumm += AmdDevices.instance.list[i].algos[Algos.instance.gpuList[j]]!! * AmdDevices.instance.list[i].count
                }
            }

            summMap[Algos.instance.gpuList[j]] = tempSumm
        }

        return summMap
    }
}