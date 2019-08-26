package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.EarningResponse
import by.lebedev.domain.entities.CoinProfitability
import io.reactivex.Observable
import io.reactivex.Single

interface GetProfitableCoinsUseCase {

    fun fetch(selectedItem:Int, hashrate:Long, device:String): Single<ArrayList<CoinProfitability>>
}