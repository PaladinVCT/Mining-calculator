package by.lebedev.data.repository.entities.history


import com.google.gson.annotations.SerializedName

data class RateHistoryDataResponse(
    @SerializedName("data")
    val data: List<DataResponse>,
    @SerializedName("timestamp")
    val timestamp: Long
)

data class DataResponse(
    @SerializedName("priceUsd")
    val priceUsd: Double,
    @SerializedName("time")
    val time: Long
)