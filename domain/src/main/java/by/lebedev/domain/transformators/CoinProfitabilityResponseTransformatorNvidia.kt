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
                    "https://www.coincalculators.io/" + nvidiaCoinListProfitabilityResponse.get(i).image,
                    nvidiaCoinListProfitabilityResponse.get(i).name,
                    nvidiaCoinListProfitabilityResponse.get(i).symbol,
                    -1,
                    nvidiaCoinListProfitabilityResponse.get(i).algorithm,
                    nvidiaCoinListProfitabilityResponse.get(i).yourHashrate.div(
                        HashTypeConfigurator().getDigitsFromType(
                            HashTypeConfigurator().getTypeFromName(nvidiaCoinListProfitabilityResponse.get(i).algorithm)
                        )
                    ).toString(),
                    nvidiaCoinListProfitabilityResponse.get(i).revenueInDayUSD,
                    nvidiaCoinListProfitabilityResponse.get(i).revenueInDayUSD,
                    nvidiaCoinListProfitabilityResponse.get(i).revenueInMonthUSD,
                    nvidiaCoinListProfitabilityResponse.get(i).revenueInMonthUSD,
                    nvidiaCoinListProfitabilityResponse.get(i).rewardsInDay,
                    nvidiaCoinListProfitabilityResponse.get(i).rewardsInMonth,
                    nvidiaCoinListProfitabilityResponse.get(i).volume_usd
                )
            )
        }
        return coinList
    }
}