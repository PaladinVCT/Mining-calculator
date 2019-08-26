package by.lebedev.domain.entities

import by.lebedev.data.repository.entities.CoinProfitabilityResponse

data class CoinProfitability(

    val imageUrl:String,
    val coinName: String,
    val algoId: Int,
    val algoName: String,
    val hashrateAuto: Double,
    val rewardDayUsd: Double,
    val rewardMonthUsd: Double,
    val rewardDayCoins: Double,
    val rewardMonthCoins: Double,
    val volumeUsd: Double
)