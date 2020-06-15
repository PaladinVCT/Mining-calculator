package by.lebedev.domain.usecase

import by.lebedev.domain.entities.CoinGeckoCoin
import io.reactivex.Single

interface GetCoinGeckoCoinsUseCase {

    fun fetch(): Single<ArrayList<CoinGeckoCoin>>
}