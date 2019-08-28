package by.lebedev.domain.entities

data class CoinProfitability(

    var imageUrl:String,
    var coinName: String,
    var coinTicker: String,
    var algoId: Int,
    var algoName: String,
    var hashrateAuto: String,
    var rewardDayUsd: Double,
    var rewardDayUsdActual: Double,
    var rewardMonthUsd: Double,
    var rewardMonthUsdActual: Double,
    var rewardDayCoins: Double,
    var rewardMonthCoins: Double,
    var volumeUsd: Double
)