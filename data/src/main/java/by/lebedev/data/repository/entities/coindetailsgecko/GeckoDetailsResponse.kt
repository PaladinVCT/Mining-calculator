package by.lebedev.data.repository.entities.coindetailsgecko


import com.google.gson.annotations.SerializedName

data class GeckoDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("image")
    val image: ImageResponse,
    @SerializedName("links")
    val links: LinksResponse,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("market_data")
    val marketData: MarketDataResponse
) {
    data class ImageResponse(
        @SerializedName("large")
        val large: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("thumb")
        val thumb: String
    )

    data class LinksResponse(
        @SerializedName("blockchain_site")
        val blockchainSite: List<String>,
        @SerializedName("homepage")
        val homepage: List<String>
    )

    data class MarketDataResponse(
        @SerializedName("total_supply")
        val totalSupply: Double,
        @SerializedName("circulating_supply")
        val circulatingSupply: Double,
        @SerializedName("current_price")
        val currentPrice: CurrentPriceResponse,
        @SerializedName("high_24h")
        val high24h: High24hResponse,
        @SerializedName("low_24h")
        val low24h: Low24hResponse,
        @SerializedName("price_change_percentage_24h")
        val priceChangePercentage24h: Double,
        @SerializedName("market_cap")
        val marketCap: MarketCapResponse,
        @SerializedName("total_volume")
        val totalVolume: TotalVolumeResponse
    ) {
        data class CurrentPriceResponse(
            @SerializedName("usd")
            val usd: Double
        )

        data class High24hResponse(
            @SerializedName("usd")
            val usd: Double
        )

        data class Low24hResponse(
            @SerializedName("usd")
            val usd: Double
        )

        data class MarketCapResponse(
            @SerializedName("usd")
            val usd: Double
        )

        data class TotalVolumeResponse(
            @SerializedName("usd")
            val usd: Double
        )
    }
}