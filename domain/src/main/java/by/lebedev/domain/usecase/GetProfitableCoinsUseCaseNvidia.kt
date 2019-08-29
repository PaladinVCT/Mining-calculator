package by.lebedev.domain.usecase

import android.util.Log
import by.lebedev.data.repository.provideApiNvidia
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.transformators.CoinProfitabilityResponseTransformatorNvidia
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetProfitableCoinsUseCaseNvidia(
) : GetProfitableCoinsUseCase {

    override fun fetch(selectedItem: Int, hashrate: Long, device: String): Single<ArrayList<CoinProfitability>> {

        return provideApiNvidia().getEarningsNvidia(hashrate, Algos.instance.list.get(selectedItem))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.e("AAA", it.get(0).toString())
                CoinProfitabilityResponseTransformatorNvidia().execute(it)
            }
    }
}