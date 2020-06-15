package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinGecko
import by.lebedev.data.repository.entities.coingecko.CoinGeckoResponse
import by.lebedev.domain.entities.CoinGeckoCoin
import by.lebedev.domain.transformators.CoinGeckoResponseToCoinGeckoTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCoinGeckoCoinsUseCaseImpl : GetCoinGeckoCoinsUseCase {
    override fun fetch(): Single<ArrayList<CoinGeckoCoin>> {

        return NetProviderCoinGecko.provideApi().getAllGeckoCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                CoinGeckoResponseToCoinGeckoTransformator().execute(it)
            }
    }
}