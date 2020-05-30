package by.lebedev.data.repository

import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetProviderCoinCap {

    private const val BASE_URL = "https://api.coincap.io/v2/"
    private const val API_KEY_NAME = "X-CMC_PRO_API_KEY"
    private const val API_KEY_VALUE = "eb58ea2f-1d43-405f-bb97-ef01946db5fb"

    private var httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader(API_KEY_NAME, API_KEY_VALUE).build()
            chain.proceed(request)
        }

    fun provideApi(): CoinCapApi {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(CoinCapApi::class.java)
    }
}