package alifyz.com.popseries.network

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.model.Series
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularEndpoint{

    @GET("discover/tv")
    fun getPopularSeries(@Query("api_key") apikey : String) : Call<Series>
}