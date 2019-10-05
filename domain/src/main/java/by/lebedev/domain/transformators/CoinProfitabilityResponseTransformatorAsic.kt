package by.lebedev.domain.transformators

import by.lebedev.data.repository.entities.AsicProfitabilityResponse
import by.lebedev.data.repository.entities.CryptonightCoinProfitabilityResponse
import by.lebedev.data.repository.entities.NvidiaCoinProfitabilityResponse
import by.lebedev.domain.entities.CoinProfitability

class CoinProfitabilityResponseTransformatorAsic {


    fun execute(asicListProfitabilityResponse: ArrayList<AsicProfitabilityResponse>): ArrayList<CoinProfitability> {

        val coinList: ArrayList<CoinProfitability> = ArrayList()

        for (i in 0 until asicListProfitabilityResponse.size) {

            coinList.add(
                i, CoinProfitability(
                    "https://www.coincalculators.io/" + asicListProfitabilityResponse.get(i).image,
                    asicListProfitabilityResponse.get(i).name,
                    asicListProfitabilityResponse.get(i).symbol,
                    -1,
                    asicListProfitabilityResponse.get(i).algorithm,
                    asicListProfitabilityResponse.get(i).yourHashrate.div(
                        HashTypeConfigurator().getDigitsFromType(
                            HashTypeConfigurator().getTypeFromName(asicListProfitabilityResponse.get(i).algorithm)
                        )
                    ).toString(),
                    asicListProfitabilityResponse.get(i).revenueInDayUSD,
                    asicListProfitabilityResponse.get(i).revenueInDayUSD,
                    asicListProfitabilityResponse.get(i).revenueInMonthUSD,
                    asicListProfitabilityResponse.get(i).revenueInMonthUSD,
                    asicListProfitabilityResponse.get(i).rewardsInDay,
                    asicListProfitabilityResponse.get(i).rewardsInMonth,
                    asicListProfitabilityResponse.get(i).volume_usd
                )
            )
        }
        return coinList
    }
}