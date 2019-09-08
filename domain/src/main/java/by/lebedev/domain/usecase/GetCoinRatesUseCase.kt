package by.lebedev.domain.usecase

import by.lebedev.data.repository.NetProviderCoinCap
import by.lebedev.data.repository.entities.coincap.CoinCapInfoResponse
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.entities.CoinRate
import io.reactivex.Single

interface GetCoinRatesUseCase {

    fun fetch(): Single<ArrayList<CoinRate>>
}