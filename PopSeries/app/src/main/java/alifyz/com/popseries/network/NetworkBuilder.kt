package alifyz.com.popseries.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkBuilder {

    internal val BASE_URL = "https://api.themoviedb.org/3/"

    fun init() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}