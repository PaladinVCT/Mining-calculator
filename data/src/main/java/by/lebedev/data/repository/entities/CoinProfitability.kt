package by.lebedev.data.repository.entities

data class CoinProfitability(

    val coin_id: Int,
    val coin_name: String,
    val coin_ticker: String,
    val algo_id: Int,
    val algo_name: String,
    val algo_asic: Boolean,
    val hashrate_auto: Double,
    val reward_day_usd: Double,
    val reward_month_usd: Double,
    val reward_day_coins: Double,
    val reward_month_coins: Double,
    val volume_24h_usd: Double
)