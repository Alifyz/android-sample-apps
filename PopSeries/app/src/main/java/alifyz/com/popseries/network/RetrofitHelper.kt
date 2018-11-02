package alifyz.com.popseries.network

import alifyz.com.popseries.model.PopularModel
import alifyz.com.popseries.model.TopModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitHelper {

    companion object {
        fun getInstance() : Retrofit {
            return  Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}

interface SeriesEndpoint{

    @GET("discover/tv")
    fun getPopularSeries(@Query("api_key") apikey : String) : Call<PopularModel>

    @GET("trending/tv/week")
    fun getTopSeries(@Query("api_key") apikey : String) : Call<TopModel>
}