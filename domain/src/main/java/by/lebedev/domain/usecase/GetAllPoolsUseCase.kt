package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.domain.entities.Pools

interface GetAllPoolsUseCase {

    fun fetch(context: Context): Pools
}