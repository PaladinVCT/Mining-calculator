package by.lebedev.domain.entities


import by.lebedev.data.repository.entities.coindetailsgecko.GeckoDetailsResponse
import com.google.gson.annotations.SerializedName

data class GeckoDetails(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("links")
    val links: Links,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("market_data")
    val marketData: MarketData
) {
    data class Image(
        @SerializedName("large")
        val large: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("thumb")
        val thumb: String
    )

    data class Links(
        @SerializedName("blockchain_site")
        val blockchainSite: List<String>,
        @SerializedName("homepage")
        val homepage: List<String>
    )

    data class MarketData(
        @SerializedName("total_supply")
        val totalSupply: Double,
        @SerializedName("circulating_supply")
        val circulatingSupply: Double,
        @SerializedName("current_price")
        val currentPrice: CurrentPrice,
        @SerializedName("high_24h")
        val high24h: High24h,
        @SerializedName("low_24h")
        val low24h: Low24h,
        @SerializedName("price_change_percentage_24h")
        val priceChangePercentage24h: Double,
        @SerializedName("market_cap")
        val marketCap: MarketCap,
        @SerializedName("total_volume")
        val totalVolume: TotalVolume
    ) {
        data class CurrentPrice(
            @SerializedName("usd")
            val usd: Double
        )

        data class High24h(
            @SerializedName("usd")
            val usd: Double
        )

        data class Low24h(
            @SerializedName("usd")
            val usd: Double
        )

        data class MarketCap(
            @SerializedName("usd")
            val usd: Double
        )

        data class TotalVolume(
            @SerializedName("usd")
            val usd: Double
        )
    }
}