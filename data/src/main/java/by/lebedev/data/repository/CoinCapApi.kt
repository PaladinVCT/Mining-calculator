package by.lebedev.data.repository


import by.lebedev.data.repository.entities.coincap.CoinCapDataResponse
import by.lebedev.data.repository.entities.coindetails.CoinDetailsResponse
import by.lebedev.data.repository.entities.history.RateHistoryDataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinCapApi {

    @GET("assets?limit=2000")
    fun getAllCoins(): Single<CoinCapDataResponse>

    @GET("assets/{coinName}/history")
    fun getHistoryRates(
        @Path("coinName") coinName: String,
        @Query("interval") interval: String
    ): Single<RateHistoryDataResponse>

    @GET("assets/{coinName}")
    fun getCoinDetails(
        @Path("coinName") coinName: String
    ): Single<CoinDetailsResponse>

}