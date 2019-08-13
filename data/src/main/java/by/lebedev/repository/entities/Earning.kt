package by.lebedev.repository.entities

data class Earning (

   val hashrate: Double,
val device: String,
 val updatetime: String,
val coins: ArrayList<CoinProfitability>
)