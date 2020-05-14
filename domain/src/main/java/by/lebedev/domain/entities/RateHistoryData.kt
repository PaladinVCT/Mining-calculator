package by.lebedev.domain.entities



data class RateHistoryData(
    val data: List<Data>,
    val timestamp: Long
)

data class Data(
    val priceUsd: Double,
    val time: Long
)