package by.lebedev.data.repository.entities

import java.util.*

data class CryptonightEarningResponse(

    var hashrate: Double,
    var device: String,
    var updatetime: String,
    var coins: ArrayList<CryptonightCoinProfitabilityResponse>
)