package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.CryptonightCoinsResponse
import by.lebedev.domain.entities.Data
import by.lebedev.domain.entities.RateHistoryData
import io.reactivex.Single

interface GetHistoryRatesUseCase {

    fun fetch(coinName: String, interval: String): Single<ArrayList<Data>>
}