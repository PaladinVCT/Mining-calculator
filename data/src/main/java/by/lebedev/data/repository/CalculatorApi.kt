package by.lebedev.data.repository

import by.lebedev.data.repository.entities.AlgoResponse
import by.lebedev.data.repository.entities.CoinsResponse
import by.lebedev.data.repository.entities.EarningResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface CalculatorApi  {

    @GET("coins")
    fun getAllCoins(): Single<ArrayList<CoinsResponse>>

    @GET("algos")
    fun getAllAlgos(): Single<ArrayList<AlgoResponse>>

    @GET("earnings/")
    fun getEarningInfo(@Query("hashrate") hashrate: Long, @Query("device") device: String): Single<ArrayList<EarningResponse>>

}