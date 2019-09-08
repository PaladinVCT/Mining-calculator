package by.lebedev.data.repository


import by.lebedev.data.repository.entities.coincap.CoinCapInfoResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CoinCapApi {

    @GET("v1/cryptocurrency/listings/latest")
    fun loadData(): Single<CoinCapInfoResponse>


}