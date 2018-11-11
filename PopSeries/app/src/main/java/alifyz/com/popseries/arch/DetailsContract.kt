package alifyz.com.popseries.arch

import alifyz.com.popseries.model.SeriesModel
import android.view.Window

interface DetailsContract {

    interface View : BaseView<DetailsPresenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showEmptyContent()

        fun showOffline()

        fun showSavedAlert()

        fun bindViews(seriesObject : SeriesModel.SeriesMetaData)

        fun showErrorScreen()

        fun setRatings(voteAverage: Double?) : Float

        fun setRateCount(seriesDetail: SeriesModel.SeriesMetaData) : String

    }


    interface Presenter : BasePresenter {
        fun extractData(intentData : String)
        fun load()
        fun setFlags(window : Window)
    }
}