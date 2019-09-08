package by.lebedev.data.repository.entities.coincap

data class CoinCap(
    var id: Int,
    var name: String,
    var symbol: String,
    var quote : Quote,
    var url: String = "https://s2.coinmarketcap.com/static/img/coins/32x32/$id.png"

)