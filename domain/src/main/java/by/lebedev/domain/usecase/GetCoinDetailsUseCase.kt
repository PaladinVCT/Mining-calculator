package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.CryptonightCoinsResponse
import by.lebedev.domain.entities.Data
import by.lebedev.domain.entities.Details
import by.lebedev.domain.entities.RateHistoryData
import io.reactivex.Single

interface GetCoinDetailsUseCase {

    fun fetch(coinName: String): Single<Details>
}