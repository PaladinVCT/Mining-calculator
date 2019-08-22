package by.lebedev.domain.coins

import by.lebedev.data.repository.entities.EarningResponse
import by.lebedev.domain.entities.CoinProfitability
import io.reactivex.Single

interface GetProfitableCoinsUseCase {

    fun getProfitableCoins(selectedItem:Int,hashrate:Long,device:String)
}