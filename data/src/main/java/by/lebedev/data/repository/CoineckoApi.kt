package by.lebedev.data.repository

import by.lebedev.data.repository.entities.coingecko.CoinGeckoResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CoineckoApi {

    @GET("coins/list")
    fun getAllGeckoCoins(): Single<CoinGeckoResponse>
}
