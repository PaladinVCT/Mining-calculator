package by.lebedev.domain.usecase

import android.util.Log
import by.lebedev.data.repository.entities.EarningResponse
import by.lebedev.data.repository.provideApi
import by.lebedev.domain.CoinProfitabilityResponseTransformator
import by.lebedev.domain.entities.CoinProfitability
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetProfitableCoinsUseCaseCryptonight(
) : GetProfitableCoinsUseCase {

    override fun fetch(selectedItem: Int, hashrate: Long, device: String): Single<ArrayList<CoinProfitability>> {

        return provideApi().getEarningInfo(hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.e("AAA", it.get(0).toString())
                CoinProfitabilityResponseTransformator().execute(it.get(0).coins)
            }
    }
}