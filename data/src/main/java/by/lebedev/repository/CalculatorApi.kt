package by.lebedev.repository

import by.lebedev.repository.entities.Algo
import by.lebedev.repository.entities.Coin
import by.lebedev.repository.entities.Earning
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CalculatorApi  {

    @GET("coins")
    fun getAllCoins(): Single<ArrayList<Coin>>

//    @GET("history?{coinId}")
//    fun getCoinHistory(@Path("coinId") coinId: Int): Single<ArrayList<coinHistory>>

    @GET("algos")
    fun getAllAlgos(): Single<ArrayList<Algo>>

    @GET("earnings/?hashrate={hashrate}&device={device}")
    fun getGeneralInfo(@Path("hashrate") hashrate: Long, @Path("device") device: String): Single<ArrayList<Earning>>

}