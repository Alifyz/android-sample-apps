package alifyz.com.popseries.home

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.model.Series
import alifyz.com.popseries.network.PopularEndpoint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val endpoint = retrofit.create(PopularEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<Series> {
            override fun onResponse(call: Call<Series>?, response: Response<Series>?) {
               Log.d("Retrofit: ", "Success")
                val response_body = response?.body()
            }

            override fun onFailure(call: Call<Series>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
