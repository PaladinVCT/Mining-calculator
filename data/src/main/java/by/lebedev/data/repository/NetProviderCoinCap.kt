package by.lebedev.data.repository

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetProviderCoinCap {

    private val BASE_URL = "https://pro-api.coinmarketcap.com/"

    // выставлены таймауты на соединение с сервером
    var httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder().addHeader("x-cmc_pro_api_key", "587a787c-2a32-44f9-8a5a-40470f01ce6f").build()
                return chain.proceed(request)
            }
        })

    fun create(): CoinCapApi {
        Log.e("AAA", "api service")
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(CoinCapApi::class.java);
    }


}
