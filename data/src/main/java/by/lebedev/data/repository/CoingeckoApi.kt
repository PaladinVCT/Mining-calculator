package by.lebedev.data.repository

import by.lebedev.data.repository.entities.coindetailsgecko.GeckoDetailsResponse
import by.lebedev.data.repository.entities.coingecko.CoinGeckoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoingeckoApi {

    @GET("coins/list")
    fun getAllGeckoCoins(): Single<CoinGeckoResponse>

    @GET("coins/{coinName}")
    fun getGeckoCoinDetails(@Path("coinName") coinName: String): Single<GeckoDetailsResponse>

}
