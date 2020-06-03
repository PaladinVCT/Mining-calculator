package by.lebedev.domain.entities



data class CoinDetails(
    val `data`: Details,
    val timestamp: Long
)

data class Details(
    val changePercent24Hr: Double,
    val id: String,
    val marketCapUsd: Double,
    val maxSupply: Double,
    val name: String,
    val priceUsd: Double,
    val rank: Double,
    val supply: Double,
    val symbol: String,
    val volumeUsd24Hr: Double,
    val vwap24Hr: Double
)