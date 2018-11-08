package alifyz.com.popseries.ui

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.adapter.HomeSeriesAdapter
import alifyz.com.popseries.arch.PopularUIContract
import alifyz.com.popseries.model.SeriesModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragments : Fragment() {

    val gridLayout = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onResume() {
        super.onResume()
        progress.visibility = View.VISIBLE
        recyclerview_popular.visibility = View.GONE

        val retrofit = RetrofitHelper.getInstance()

        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<SeriesModel> {
            override fun onResponse(call: Call<SeriesModel>?, response: Response<SeriesModel>?) {
                Log.d("RetrofitHelper: ", "Success")
                storeSeries(response)
            }

            override fun onFailure(call: Call<SeriesModel>?, t: Throwable?) {
                Log.d("RetrofitHelper: ", "Failed")
                TODO("not implemented")
            }
        })
    }

    fun storeSeries(response: Response<SeriesModel>?) {
        val response_body = response?.body()
        progress.visibility = View.GONE
        recyclerview_popular.visibility = View.VISIBLE

        recyclerview_popular.layoutManager = gridLayout
        recyclerview_popular.adapter = HomeSeriesAdapter(context!!, response_body!!)
    }
}