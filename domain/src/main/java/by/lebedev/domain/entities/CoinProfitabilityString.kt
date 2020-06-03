package by.lebedev.domain.entities

data class CoinProfitabilityString(

    var imageUrl:String,
    var coinName: String,
    var coinTicker: String,
    var algoId: Int,
    var algoName: String,
    var hashrateAuto: String,
    var rewardDayUsd: String,
    var rewardMonthUsd: String,
    var rewardDayCoins: String,
    var rewardMonthCoins: String,
    var volumeUsd: String,
    var showAlert:Boolean
)