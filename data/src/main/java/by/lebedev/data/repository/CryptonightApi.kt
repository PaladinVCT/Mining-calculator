package by.lebedev.data.repository

import by.lebedev.data.repository.entities.CryptonightAlgoResponse
import by.lebedev.data.repository.entities.CryptonightCoinsResponse
import by.lebedev.data.repository.entities.CryptonightEarningResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface CryptonightApi  {

    @GET("cryptonightCoins")
    fun getAllCoinsCryptonight(): Single<ArrayList<CryptonightCoinsResponse>>

    @GET("algos")
    fun getAllAlgos(): Single<ArrayList<CryptonightAlgoResponse>>

    @GET("earnings/")
    fun getEarningsCryptonight(@Query("hashrate") hashrate: Double, @Query("device") device: String): Single<ArrayList<CryptonightEarningResponse>>

}