package alifyz.com.popseries.arch

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.model.SeriesModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularPresenter : PopularUIContract.Presenter {

    override fun start() {
        val retrofit = RetrofitHelper.getInstance()

        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<SeriesModel> {
            override fun onResponse(call: Call<SeriesModel>?, response: Response<SeriesModel>?) {
                Log.d("RetrofitHelper: ", "Success")
            }

            override fun onFailure(call: Call<SeriesModel>?, t: Throwable?) {
                Log.d("RetrofitHelper: ", "Failed")
                TODO("not implemented")
            }
        })
    }

    override fun openDetails() {

    }
}