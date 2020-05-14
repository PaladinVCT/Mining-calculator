package by.lebedev.domain.entities

data class CoinData(
    var changePercent24Hr: Double,
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