package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinGecko
import by.lebedev.data.repository.entities.coindetailsgecko.GeckoDetailsResponse
import by.lebedev.domain.entities.GeckoDetails
import by.lebedev.domain.transformators.CoinGeckoResponseToCoinGeckoTransformator
import by.lebedev.domain.transformators.GeckoDetailsResponseToGeckoTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetGeckoDetailsUseCaseImpl : GetGeckoDetailsUseCase {
    override fun fetch(coinName: String): Single<GeckoDetails> {

        return NetProviderCoinGecko.provideApi().getGeckoCoinDetails(coinName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                GeckoDetailsResponseToGeckoTransformator().execute(it)
            }
    }
}