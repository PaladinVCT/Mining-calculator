package by.lebedev.data.repository.entities.coincap


import com.google.gson.annotations.SerializedName

data class CoinCapDataResponse(
    @SerializedName("data")
    val data: List<DataResponse>,
    @SerializedName("timestamp")
    val timestamp: Long
)

data class DataResponse(
    @SerializedName("changePercent24Hr")
    var changePercent24Hr: Double,
    @SerializedName("id")
    var id: String,
    @SerializedName("marketCapUsd")
    var marketCapUsd: Double,
    @SerializedName("maxSupply")
    var maxSupply: Double,
    @SerializedName("name")
    var name: String,
    @SerializedName("priceUsd")
    var priceUsd: Double,
    @SerializedName("rank")
    var rank: String,
    @SerializedName("supply")
    var supply: Double,
    @SerializedName("symbol")
    var symbol: String,
    @SerializedName("volumeUsd24Hr")
    var volumeUsd24Hr: Double,
    @SerializedName("vwap24Hr")
    var vwap24Hr: Double,
    var url: String?

)