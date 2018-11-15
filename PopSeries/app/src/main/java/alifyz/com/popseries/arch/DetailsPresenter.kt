package alifyz.com.popseries.arch

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.model.SeriesDetailModel
import alifyz.com.popseries.model.SeriesModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.view.Window
import android.view.WindowManager
import com.google.gson.GsonBuilder
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsPresenter(private var view : DetailsContract.View) : DetailsContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {}

    override fun extractData(intentData: String) {
        view.bindViews(deserialize(intentData))
    }

    override fun loadAdditionalInformation(id: String, language: String, appendToResponse : String) {

        val api = BuildConfig.API_KEY

        val retrofit = RetrofitHelper.getInstance()
        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getShowDetails(id,api,language,appendToResponse)


        call.enqueue(object : Callback<SeriesDetailModel> {
            override fun onFailure(call: Call<SeriesDetailModel>, t: Throwable) {
                TODO("not implemented")
            }

            override fun onResponse(call: Call<SeriesDetailModel>, response: Response<SeriesDetailModel>) {
                val credits = response.body()?.credits
                view.setAdditionalViews(credits)
            }
        })
    }

    override fun deserialize(intentData: String): SeriesModel.SeriesMetaData {
        val builder = GsonBuilder()
        val gson = builder.create()
        return  gson.fromJson(intentData, SeriesModel.SeriesMetaData::class.java)
    }

    override fun extractId(intentData: String) : String {
       return deserialize(intentData).id.toString()
    }

    override fun setFlags(window : Window) {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

}