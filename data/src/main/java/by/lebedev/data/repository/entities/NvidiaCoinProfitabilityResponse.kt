package by.lebedev.data.repository.entities

data class NvidiaCoinProfitabilityResponse(

    var name: String,
    var image: String,
    var algorithm: String,
    var symbol: String,
    var revenueInDayUSD: Double,
    var rewardsInDay: Double,
    var revenueInMonthUSD: Double,
    var rewardsInMonth: Double,
    var volume_usd: Double,
    var yourHashrate: Double
)
