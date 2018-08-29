package alifyz.com.popseries.network

import alifyz.com.popseries.model.PopularModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesEndpoint{

    @GET("discover/tv")
    fun getPopularSeries(@Query("api_key") apikey : String) : Call<PopularModel>
}