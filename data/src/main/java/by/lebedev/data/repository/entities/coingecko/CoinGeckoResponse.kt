package by.lebedev.data.repository.entities.coingecko


import com.google.gson.annotations.SerializedName

class CoinGeckoResponse : ArrayList<CoinGeckoResponseItem>()

data class CoinGeckoResponseItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)