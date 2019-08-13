package by.lebedev.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CalculatorApi  {

    @GET("{coin}/prices")
    fun getPrice(@Path("coin") coin: String): Single<Price>

    @GET("{coin}/pool/hashrate")
    fun getHashrate(@Path("coin") coin: String): Single<Hashrate>

    @GET("{coin}/pool/activeminers")
    fun getMiners(@Path("coin") coin: String): Single<Miners>

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