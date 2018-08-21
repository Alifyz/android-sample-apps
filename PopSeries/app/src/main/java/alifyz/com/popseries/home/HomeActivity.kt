package alifyz.com.popseries.home

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.model.Series
import alifyz.com.popseries.network.PopularEndpoint
import alifyz.com.popseries.ui.FavoriteFragment
import alifyz.com.popseries.ui.PopularFragments
import alifyz.com.popseries.ui.TopFragments
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        startFragment()

        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
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
                TODO("not implemented")
            }
        })
    }

    private fun startFragment() {
        val homeFragment = PopularFragments()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment, homeFragment)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bottom_popular -> {
               val popularFragment = PopularFragments()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, popularFragment)
                       .commit()
            }
            R.id.bottom_top -> {
               val topFragment = TopFragments()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, topFragment)
                       .commit()
            }
            R.id.bottom_fav -> {
               val favoriteFragment = FavoriteFragment()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, favoriteFragment)
                       .commit()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
