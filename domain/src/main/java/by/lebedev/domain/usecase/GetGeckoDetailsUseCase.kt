package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.coindetailsgecko.GeckoDetailsResponse
import by.lebedev.domain.entities.GeckoDetails
import io.reactivex.Single

interface GetGeckoDetailsUseCase {

    fun fetch(coinName: String): Single<GeckoDetails>
}