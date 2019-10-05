package by.lebedev.domain.usecase

import android.util.Log
import by.lebedev.data.repository.provideApiAsic
import by.lebedev.data.repository.provideApiNvidia
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.transformators.CoinProfitabilityResponseTransformatorAsic
import by.lebedev.domain.transformators.CoinProfitabilityResponseTransformatorNvidia
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetProfitableCoinsUseCaseAsic(
) : GetProfitableCoinsUseCase {

    override fun fetch(selectedItem: Int, hashrate: Double, device: String): Single<ArrayList<CoinProfitability>> {

        return provideApiAsic().getEarningsAsic(hashrate, Algos.instance.asicList.get(selectedItem))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.e("AAA", it.get(0).toString())
                CoinProfitabilityResponseTransformatorAsic().execute(it)
            }
    }
}