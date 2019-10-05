package by.lebedev.data.repository

import by.lebedev.data.repository.entities.AsicProfitabilityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface AsicApi {

    @GET("api")
    fun getEarningsAsic(
        @Query("hashrate") hashrate: Double, @Query("algorithm") algo: String
    ): Single<ArrayList<AsicProfitabilityResponse>>
}