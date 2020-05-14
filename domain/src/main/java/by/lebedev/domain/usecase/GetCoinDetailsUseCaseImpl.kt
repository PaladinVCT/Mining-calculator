package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinCap
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.entities.Details
import by.lebedev.domain.transformators.CoinCapInfoResponseResponseTransformator
import by.lebedev.domain.transformators.CoinDetailsResponseTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCoinDetailsUseCaseImpl : GetCoinDetailsUseCase {
    override fun fetch(coinName: String): Single<Details> {

        return NetProviderCoinCap.provideApi().getCoinDetails(coinName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                CoinDetailsResponseTransformator().execute(it)
            }
    }
}