package by.lebedev.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CalculatorApi  {

    @GET("coins")
    fun getAllCoins(): Single<Coins>

    @GET("history?{coinId}")
    fun getCoinHistory(@Path("coinId") coinId: Int): Single<CoinHistory>

    @GET("algos")
    fun getAllAlgos(): Single<Algos>

    @GET("{coin}/user/{wallet}")
    fun getGeneralInfo(@Path("coin") coin: String, @Path("wallet") wallet: String): Single<GeneralInfo>

    @GET("{coin}/approximated_earnings/{hashrate}")
    fun getProfit(@Path("coin") coin: String, @Path("hashrate") hashrate: Double): Single<Profit>

    @GET("{coin}/payments/{wallet}")
    fun getPayments(@Path("coin") coin: String, @Path("wallet") wallet: String): Single<Payments>

    @GET("{coin}/workers/{wallet}")
    fun getWorkers(@Path("coin") coin: String, @Path("wallet") wallet: String): Single<Workers>

    @GET("{coin}/hashratechart/{wallet}")
    fun getChart(@Path("coin") coin: String, @Path("wallet") wallet: String): Single<ChartInfo>

}