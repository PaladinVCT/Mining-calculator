package by.lebedev.domain.usecase

import android.util.Log
import by.lebedev.data.repository.provideApiCryptonight
import by.lebedev.domain.transformators.CoinProfitabilityResponseTransformatorCryptonight
import by.lebedev.domain.entities.CoinProfitability
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetProfitableCoinsUseCaseCryptonight(
) : GetProfitableCoinsUseCase {

    override fun fetch(selectedItem: Int, hashrate: Double, device: String): Single<ArrayList<CoinProfitability>> {

        return provideApiCryptonight().getEarningsCryptonight(hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.e("AAA", it.get(0).toString())
                CoinProfitabilityResponseTransformatorCryptonight().execute(it.get(0).coins)
            }
    }
}