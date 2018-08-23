package alifyz.com.popseries.ui

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.adapter.SeriesAdapter
import alifyz.com.popseries.database.SeriesDatabase
import alifyz.com.popseries.model.Series
import alifyz.com.popseries.network.PopularEndpoint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_popular.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularFragments : Fragment() {

    val gridLayout = GridLayoutManager(
                    context,
                    2,
                    GridLayoutManager.VERTICAL,
                    false)

    val mDatabase = context?.let { SeriesDatabase.getInstance(it) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onResume() {
        super.onResume()
        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val endpoint = retrofit.create(PopularEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<Series> {
            override fun onResponse(call: Call<Series>?, response: Response<Series>?) {
                Log.d("Retrofit: ", "Success")
                storeSeries(response)
            }
            override fun onFailure(call: Call<Series>?, t: Throwable?) {
                TODO("not implemented")
            }
        })
    }

    fun storeSeries(response: Response<Series>?) {
        val response_body = response?.body()

        doAsync {
            TODO("Fetch and Store in the Database")
        }
        recyclerview_popular.layoutManager = gridLayout
        recyclerview_popular.adapter = SeriesAdapter(context!!, response_body!!)
    }
}