package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.CoinsResponse
import by.lebedev.data.repository.provideApi
import io.reactivex.Single

class GetCoinsUseCaseCryptonight(
) : GetCoinsUseCase {

    override fun fetch(): Single<ArrayList<CoinsResponse>> {

        return provideApi().getAllCoins()


    }
}