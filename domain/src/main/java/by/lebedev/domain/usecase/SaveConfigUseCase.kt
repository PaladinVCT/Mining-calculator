package by.lebedev.domain.usecase

import android.content.Context

interface SaveConfigUseCase {

    fun execute(context: Context, name: String)
}