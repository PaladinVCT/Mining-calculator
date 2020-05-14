package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.CryptonightCoinProfitabilityResponse
import by.lebedev.data.repository.entities.NvidiaCoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityResponseTransformatorNvidia {


    fun execute(nvidiaCoinListProfitabilityResponse: ArrayList<NvidiaCoinProfitabilityResponse>): ArrayList<CoinProfitability> {

        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until nvidiaCoinListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.coincalculators.io/" + nvidiaCoinListProfitabilityResponse[i].image,
                    nvidiaCoinListProfitabilityResponse[i].name,
                    nvidiaCoinListProfitabilityResponse[i].symbol,
                    -1,
                    nvidiaCoinListProfitabilityResponse[i].algorithm,
                    nvidiaCoinListProfitabilityResponse[i].yourHashrate.div(
                        HashTypeConfigurator().getDigitsFromType(
                            HashTypeConfigurator().getTypeFromName(
                                nvidiaCoinListProfitabilityResponse[i].algorithm)
                        )
                    ).toString(),
                    nvidiaCoinListProfitabilityResponse[i].revenueInDayUSD,
                    nvidiaCoinListProfitabilityResponse[i].revenueInDayUSD,
                    nvidiaCoinListProfitabilityResponse[i].revenueInMonthUSD,
                    nvidiaCoinListProfitabilityResponse[i].revenueInMonthUSD,
                    nvidiaCoinListProfitabilityResponse[i].rewardsInDay,
                    nvidiaCoinListProfitabilityResponse[i].rewardsInMonth,
                    nvidiaCoinListProfitabilityResponse[i].volume_usd
                )
            )
        }
        return coinList
    }
}