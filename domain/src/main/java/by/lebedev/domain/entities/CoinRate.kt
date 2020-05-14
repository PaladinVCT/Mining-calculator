package by.lebedev.domain.entities

data class CoinRate(
    var changePercent24Hr: String,
    var changePercent24HrColor: Int,
    var id: String,
    var marketCapUsd: Double,
    var maxSupply: Double,
    var name: String,
    var priceUsd: Double,
    var rank: String,
    var supply: Double,
    var symbol: String,
    var volumeUsd24Hr: Double,
    var vwap24Hr: Double,
    var url: String?
)