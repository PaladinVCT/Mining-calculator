package by.lebedev.domain.entities

data class CoinProfitability(

    val imageUrl:String,
    val coinName: String,
    val algoId: Int,
    val algoName: String,
    val hashrateAuto: String,
    var rewardDayUsd: String,
    val rewardMonthUsd: String,
    val rewardDayCoins: String,
    val rewardMonthCoins: String,
    val volumeUsd: String
)