package by.lebedev.data.repository

import by.lebedev.data.BuildConfig
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetProviderAsic fun provideApiAsic(): AsicApi {

    val apiSource = "https://www.coincalculators.io/"

    val clientBuilder = Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
    }
    val okhttp = clientBuilder.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(apiSource)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okhttp)
        .build()

    return retrofit.create(AsicApi::class.java)
}