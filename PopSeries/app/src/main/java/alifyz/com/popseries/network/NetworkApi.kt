package alifyz.com.popseries.network

import alifyz.com.popseries.model.Series
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("discover/tv")
    fun getSeries(
            @Query("api_key") api_key : String,
            @Query("language") language : String,
            @Query("page") page : String) : Call<Series>
}