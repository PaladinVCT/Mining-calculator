package by.lebedev.data.repository.entities

import java.util.*

data class EarningResponse(

    val hashrate: Double,
    val device: String,
    val updatetime: String,
    val coins: ArrayList<CoinProfitabilityResponse>
)