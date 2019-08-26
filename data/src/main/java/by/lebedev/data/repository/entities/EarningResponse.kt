package by.lebedev.data.repository.entities

import java.util.*

data class EarningResponse(

    var hashrate: Double,
    var device: String,
    var updatetime: String,
    var coins: ArrayList<CoinProfitabilityResponse>
)