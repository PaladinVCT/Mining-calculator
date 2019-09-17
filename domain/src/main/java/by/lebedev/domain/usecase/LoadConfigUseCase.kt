package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.entities.Config
import io.reactivex.Single

interface LoadConfigUseCase {

    fun execute(context: Context, vendor: String): Single<ArrayList<Config>>
}