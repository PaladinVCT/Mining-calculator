package by.lebedev.domain.entities

import by.lebedev.data.repository.entities.coincap.Quote

data class CoinRate (
    var id: Int,
    var name: String,
    var logoUrl: String,
    var symbol: String,
    var price: Double,
    var percent_change_24h: Double

)