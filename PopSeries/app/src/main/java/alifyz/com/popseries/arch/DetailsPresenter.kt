package alifyz.com.popseries.arch

import alifyz.com.popseries.model.SeriesModel
import android.view.Window
import android.view.WindowManager
import com.google.gson.GsonBuilder

class DetailsPresenter(private var view : DetailsContract.View) : DetailsContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
       load()
    }

    override fun extractData(intentData: String) {
        val builder = GsonBuilder()
        val gson = builder.create()
        val seriesDetail = gson.fromJson(intentData, SeriesModel.SeriesMetaData::class.java)
        view.bindViews(seriesDetail)
    }

    override fun load() {
        TODO("not implemented")
    }

    override fun setFlags(window : Window) {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

}