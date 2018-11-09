package alifyz.com.popseries.arch

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.model.SeriesModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularPresenter(
        private val popularView : PopularUIContract.View) : PopularUIContract.Presenter {

    init {
        popularView.presenter = this
    }

    override fun start() {
        loadDataFromInternet()
    }

    override fun openDetails() {

    }

    private fun loadDataFromInternet() {
        val retrofit = RetrofitHelper.getInstance()

        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<SeriesModel> {
            override fun onResponse(call: Call<SeriesModel>?, response: Response<SeriesModel>?) {
                popularView.setAdapter(response)
                popularView.setLoadingIndicator(false)
            }
            override fun onFailure(call: Call<SeriesModel>?, t: Throwable?) {
                Log.d("RetrofitHelper: ", "Failed")
                TODO("not implemented")
            }
        })
    }
}