package by.lebedev.domain.usecase

import by.lebedev.data.repository.provideApiNvidia
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.transformators.CoinProfitabilityResponseTransformatorNvidia
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetAllProfitableCoinsUseCaseNvidiaImpl(
) : GetProfitableCoinsUseCase {

    override fun fetch(selectedItem: Int, hashrate: Double, device: String): Single<ArrayList<CoinProfitability>> {

        return provideApiNvidia().getAllEarningsNvidia(hashrate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                CoinProfitabilityResponseTransformatorNvidia().execute(it)
            }
    }
}