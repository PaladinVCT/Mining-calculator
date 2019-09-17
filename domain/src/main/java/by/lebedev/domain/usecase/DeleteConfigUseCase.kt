package by.lebedev.domain.usecase

import android.content.Context

interface DeleteConfigUseCase {

    fun execute(context: Context, name: String, vendor: String)
}