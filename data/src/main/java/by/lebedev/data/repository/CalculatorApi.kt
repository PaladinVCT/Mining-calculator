package by.lebedev.data.repository

import by.lebedev.data.repository.entities.AlgoResponse
import by.lebedev.data.repository.entities.CoinResponse
import by.lebedev.data.repository.entities.EarningResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface CalculatorApi  {

    @GET("coins")
    fun getAllCoins(): Single<ArrayList<CoinResponse>>

//    @GET("history?{coinId}")
//    fun getCoinHistory(@Path("coinId") coinId: Int): Single<ArrayList<coinHistory>>

    @GET("algos")
    fun getAllAlgos(): Single<ArrayList<AlgoResponse>>

    @GET("earnings/?hashrate={hashrate}&device={device}")
    fun getGeneralInfo(@Path("hashrate") hashrate: Long, @Path("device") device: String): Single<ArrayList<EarningResponse>>

}