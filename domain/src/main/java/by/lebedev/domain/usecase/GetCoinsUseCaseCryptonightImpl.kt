package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.CryptonightCoinsResponse
import by.lebedev.data.repository.provideApiCryptonight
import io.reactivex.Single

class GetCoinsUseCaseCryptonightImpl(
) : GetCoinsUseCase {

    override fun fetch(): Single<ArrayList<CryptonightCoinsResponse>> {

        return provideApiCryptonight().getAllCoinsCryptonight()


    }
}