package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinCap
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.entities.Data
import by.lebedev.domain.entities.RateHistoryData
import by.lebedev.domain.transformators.CoinCapInfoResponseResponseTransformator
import by.lebedev.domain.transformators.RateHistoryDataResponseTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetHistoryRatesUseCaseImpl : GetHistoryRatesUseCase {
    override fun fetch(coinName: String, interval: String): Single<ArrayList<Data>> {

        return NetProviderCoinCap.provideApi().getHistoryRates(coinName, interval)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                RateHistoryDataResponseTransformator().execute(it)
            }
    }
}