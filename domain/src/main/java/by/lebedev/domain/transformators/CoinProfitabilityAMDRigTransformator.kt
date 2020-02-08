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
                if (list.get(i).algoName.equals(algos.get(j))) {

                    val coefficient = (fullHashrateMap.get(algos.get(j)))?.div(selectedHashrate)
                        ?.times(HashTypeConfigurator().getDigitsFromType(HashTypeConfigurator().getTypeFromName(algos.get(j))))
                    list.get(i).hashrateAuto = fullHashrateMap.get(algos.get(j)).toString()
                    if (coefficient != null) {
                        list.get(i).rewardDayUsd *= coefficient
                        list.get(i).rewardDayUsdActual = list.get(i).rewardDayUsd
                        list.get(i).rewardMonthUsd *= coefficient
                        list.get(i).rewardMonthUsdActual = list.get(i).rewardMonthUsd
                        list.get(i).rewardDayCoins *= coefficient
                        list.get(i).rewardMonthCoins *= coefficient

                        if (fullHashrateMap.get(algos.get(j))!=0.0){
                        modifiedList.add(list.get(i))}
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
                if (AmdDevices.instance.list.get(i).algos.get(Algos.instance.gpuList.get(j)) != null) {
                    tempSumm += AmdDevices.instance.list.get(i).algos.get(Algos.instance.gpuList.get(j))!! * AmdDevices.instance.list.get(
                        i
                    ).count
                }
            }

            summMap.put(Algos.instance.gpuList.get(j), tempSumm)

        }

        return summMap
    }

}