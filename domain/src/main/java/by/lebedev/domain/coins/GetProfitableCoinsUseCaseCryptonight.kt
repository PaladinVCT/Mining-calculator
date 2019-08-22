package by.lebedev.domain.coins

import by.lebedev.data.repository.entities.CoinProfitabilityResponse
import by.lebedev.data.repository.provideApi
import io.reactivex.Scheduler

class GetProfitableCoinsUseCaseCryptonight(
    private val workScheduler: Scheduler,
    private val postScheduler: Scheduler
) : GetProfitableCoinsUseCase {

    override fun getProfitableCoins(selectedItem: Int, hashrate: Long, device: String) :ArrayList<CoinProfitabilityResponse> {

        val d =
            provideApi().getEarningInfo(hashrate, device)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler)
                .subscribe({ result ->
                       return val l = result.coins
                }, {

                })

    }
}