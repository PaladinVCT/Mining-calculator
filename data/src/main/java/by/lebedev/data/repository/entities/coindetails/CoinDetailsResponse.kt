package by.lebedev.data.repository.entities.coindetails


import com.google.gson.annotations.SerializedName

data class CoinDetailsResponse(
    @SerializedName("data")
    val `data`: DetailsResponse,
    @SerializedName("timestamp")
    val timestamp: Long
)

data class DetailsResponse(
    @SerializedName("changePercent24Hr")
    val changePercent24Hr: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("marketCapUsd")
    val marketCapUsd: Double,
    @SerializedName("maxSupply")
    val maxSupply: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("priceUsd")
    val priceUsd: Double,
    @SerializedName("rank")
    val rank: Double,
    @SerializedName("supply")
    val supply: Double,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("volumeUsd24Hr")
    val volumeUsd24Hr: Double,
    @SerializedName("vwap24Hr")
    val vwap24Hr: Double
)