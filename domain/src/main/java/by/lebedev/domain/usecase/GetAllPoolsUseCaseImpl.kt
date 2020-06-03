package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.domain.R
import by.lebedev.domain.entities.Pools
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import kotlin.coroutines.coroutineContext

class GetAllPoolsUseCaseImpl : GetAllPoolsUseCase {
    override fun fetch(context: Context): Pools {

        val poolsJson =
            inputStreamToString(context.resources.openRawResource(R.raw.pools))

        return Gson().fromJson(poolsJson, Pools::class.java)

    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }

}