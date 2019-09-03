package by.lebedev.data.repository

import by.lebedev.data.repository.entities.CryptonightEarningResponse
import by.lebedev.data.repository.entities.NvidiaCoinProfitabilityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface NvidiaApi {

    @GET("api")
    fun getEarningsNvidia(
        @Query("hashrate") hashrate: Double, @Query("algorithm") algo: String
    ): Single<ArrayList<NvidiaCoinProfitabilityResponse>>

    @GET("api")
    fun getAllEarningsNvidia(
        @Query("hashrate") hashrate: Double
    ): Single<ArrayList<NvidiaCoinProfitabilityResponse>>
}