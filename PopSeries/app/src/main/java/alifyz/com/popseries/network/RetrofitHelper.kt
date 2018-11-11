package alifyz.com.popseries.network

import alifyz.com.popseries.model.SeriesDetailModel
import alifyz.com.popseries.model.SeriesModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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
    fun getPopularSeries(
            @Query("api_key") apikey : String,
             @Query("language") language : String) : Call<SeriesModel>

    @GET("trending/tv/week")
    fun getTopSeries(
            @Query("api_key") apikey : String,
            @Query("language") language : String) : Call<SeriesModel>

    @GET("tv/{id}")
    fun getShowDetails(
            @Path("id") id : String,
            @Query("api_key") apikey : String,
            @Query("language") language : String,
            @Query("append_to_response") appendToResponse : String) : SeriesDetailModel
}