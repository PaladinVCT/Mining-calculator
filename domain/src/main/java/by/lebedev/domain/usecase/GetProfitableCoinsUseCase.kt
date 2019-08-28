package by.lebedev.domain.usecase

import by.lebedev.domain.entities.CoinProfitability
import io.reactivex.Single

interface GetProfitableCoinsUseCase {

    fun fetch(selectedItem:Int, hashrate:Long, device:String): Single<ArrayList<CoinProfitability>>
}