package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.coindetailsgecko.GeckoDetailsResponse
import by.lebedev.domain.entities.GeckoDetails

class GeckoDetailsResponseToGeckoTransformator {

    fun execute(geckoDetailsResponse: GeckoDetailsResponse): GeckoDetails {
        val it = geckoDetailsResponse

        val blockchainList = ArrayList<String>()
        val homepageList = ArrayList<String>()

        for (element in it.links.blockchainSite) {
            blockchainList.add(element)
        }
        for (element in it.links.homepage) {
            homepageList.add(element)
        }

        return GeckoDetails(
            it.id, it.name, it.symbol, GeckoDetails.Image(
                it.image.large, it.image.small, it.image.thumb
            ), GeckoDetails.Links(blockchainList, homepageList), it.marketCapRank,
            GeckoDetails.MarketData(
                it.marketData.totalSupply,
                it.marketData.circulatingSupply,
                GeckoDetails.MarketData.CurrentPrice(it.marketData.currentPrice.usd),
                GeckoDetails.MarketData.High24h(it.marketData.high24h.usd),
                GeckoDetails.MarketData.Low24h(it.marketData.low24h.usd),
                it.marketData.priceChangePercentage24h,
                GeckoDetails.MarketData.MarketCap(it.marketData.marketCap.usd),
                GeckoDetails.MarketData.TotalVolume(it.marketData.totalVolume.usd)
            )
        )
    }
}