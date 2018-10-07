package alifyz.com.popseries.network

import alifyz.com.popseries.model.PopularModel
import alifyz.com.popseries.model.TopModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


class Retrofit {
    companion object {
        fun getInstance() {}
    }
}



interface SeriesEndpoint{

    @GET("discover/tv")
    fun getPopularSeries(@Query("api_key") apikey : String) : Call<PopularModel>

    @GET("trending/tv/week")
    fun getTopSeries(@Query("api_key") apikey : String) : Call<TopModel>
}