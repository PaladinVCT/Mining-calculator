package by.lebedev.domain.transformators

import androidx.core.content.ContextCompat
import by.lebedev.data.repository.entities.coincap.CoinCapDataResponse
import by.lebedev.domain.R
import by.lebedev.domain.entities.CoinRate
import java.text.NumberFormat
import kotlin.math.roundToInt

class CoinCapInfoResponseResponseTransformator {

    private val coinRateList: ArrayList<CoinRate> = ArrayList()
    private val nf = NumberFormat.getInstance()

    fun execute(coinCapDataResponse: CoinCapDataResponse): ArrayList<CoinRate> {
        nf.maximumFractionDigits = 2
        var changePercent24HrString = ""
        var url = ""
        var colorId = 0
        var priceUsd = 0.0

        for (i in coinCapDataResponse.data.indices) {

            changePercent24HrString = if (coinCapDataResponse.data[i].changePercent24Hr > 0) {
                "+${nf.format(coinCapDataResponse.data[i].changePercent24Hr)}%"
            } else {
                "${nf.format(coinCapDataResponse.data[i].changePercent24Hr)}%"
            }

            colorId = if (coinCapDataResponse.data[i].changePercent24Hr > 0) {
                R.color.green_percent_change
            } else {
                R.color.red_percent_change
            }

            priceUsd =((coinCapDataResponse.data[i].priceUsd * 100.0).roundToInt() / 100.0)

            url =
                "https://static.coincap.io/assets/icons/${coinCapDataResponse.data[i].symbol.toLowerCase()}@2x.png"

            coinRateList.add(
                CoinRate(
                    changePercent24HrString,
                    colorId,
                    coinCapDataResponse.data[i].id,
                    coinCapDataResponse.data[i].marketCapUsd,
                    coinCapDataResponse.data[i].maxSupply,
                    coinCapDataResponse.data[i].name,
                    priceUsd,
                    coinCapDataResponse.data[i].rank,
                    coinCapDataResponse.data[i].maxSupply,
                    coinCapDataResponse.data[i].symbol,
                    coinCapDataResponse.data[i].volumeUsd24Hr,
                    coinCapDataResponse.data[i].vwap24Hr,
                    url
                )
            )
        }
        return coinRateList
    }
}