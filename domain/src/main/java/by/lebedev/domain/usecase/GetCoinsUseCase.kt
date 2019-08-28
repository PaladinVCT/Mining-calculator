package by.lebedev.domain.usecase

import by.lebedev.data.repository.entities.CryptonightCoinsResponse
import io.reactivex.Single

interface GetCoinsUseCase {

    fun fetch(): Single<ArrayList<CryptonightCoinsResponse>>
}