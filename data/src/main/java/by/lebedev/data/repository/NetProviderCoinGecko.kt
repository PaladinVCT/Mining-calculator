package by.lebedev.data.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetProviderCoinGecko {

    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

    var logger :HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        logger.level = HttpLoggingInterceptor.Level.BODY
    }

    private var httpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    fun provideApi(): CoingeckoApi {

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(CoingeckoApi::class.java)
    }
}