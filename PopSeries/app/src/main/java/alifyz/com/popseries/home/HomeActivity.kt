package alifyz.com.popseries.home

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.network.PopularEndpoint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.orhanobut.logger.Logger
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

        val endnpoint = retrofit.create(PopularEndpoint::class.java)
        val call = endnpoint.getPopularSeries(BuildConfig.VERSION_NAME)


        val series = call.execute().body()

        Logger.d(series)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
