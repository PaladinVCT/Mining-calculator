package by.lebedev.domain.usecase

import by.lebedev.domain.entities.CoinRate
import io.reactivex.Single

interface GetCoinRatesUseCase {

    fun fetch(): Single<ArrayList<CoinRate>>
}