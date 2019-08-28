package by.lebedev.data.repository.entities

data class CryptonightCoinProfitabilityResponse(

    var coin_id: Int,
    var coin_name: String,
    var coin_ticker: String,
    var algo_id: Int,
    var algo_name: String,
    var algo_asic: Boolean,
    var hashrate_auto: Double,
    var reward_day_usd: Double,
    var reward_month_usd: Double,
    var reward_day_coins: Double,
    var reward_month_coins: Double,
    var volume_24h_usd: Double
)