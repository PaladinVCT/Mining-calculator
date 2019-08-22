package by.lebedev.domain.entities

data class CoinProfitability(

    val imageUrl:String,
    val coinId: Int,
    val coinName: String,
    val coinTicker: String,
    val algoId: Int,
    val algoName: String,
    val algoAsic: Boolean,
    val hashrateAuto: Double,
    val rewardDayUsd: Double,
    val rewardMonthUsd: Double,
    val rewardDayCoins: Double,
    val rewardMonthCoins: Double,
    val volumeUsd: Double,
    val hashrate: Double,
    val updatetime: String
)