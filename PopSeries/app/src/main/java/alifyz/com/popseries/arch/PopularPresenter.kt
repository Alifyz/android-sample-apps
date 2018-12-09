package alifyz.com.popseries.arch

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.model.SeriesModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularPresenter(private val view : PopularContract.View) : PopularContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
        loadDataFromInternet()
    }

    private fun loadDataFromInternet() {
        val retrofit = RetrofitHelper.getInstance()

        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getPopularSeries(BuildConfig.API_KEY, "en-US")

        call.enqueue(object : Callback<SeriesModel> {
            override fun onResponse(call: Call<SeriesModel>?, response: Response<SeriesModel>?) {
                view.setAdapter(response)
                view.setLoadingIndicator(false)
            }
            override fun onFailure(call: Call<SeriesModel>?, t: Throwable?) {
                Log.d("RetrofitHelper: ", "Failed")
            }
        })
    }
}