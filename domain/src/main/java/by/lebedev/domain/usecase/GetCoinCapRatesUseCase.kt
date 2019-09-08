package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinCap
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.transformators.CoinCapInfoResponseResponseTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCoinCapRatesUseCase() : GetCoinRatesUseCase {
    override fun fetch(): Single<ArrayList<CoinRate>> {

        return NetProviderCoinCap.create().loadData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                CoinCapInfoResponseResponseTransformator().execute(it)
            }
    }
}