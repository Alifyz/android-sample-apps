package alifyz.com.popseries.arch

import alifyz.com.popseries.model.Credits
import alifyz.com.popseries.model.Reviews
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

        fun setAdditionalViews(cast : Credits?)

        fun setReviews(reviews : Reviews?)

        fun setEmptyReviews()

    }


    interface Presenter : BasePresenter {
        fun extractData(intentData : String)
        fun loadAdditionalInformation(id : String, language : String, appendToResponse : String)
        fun setFlags(window : Window)
        fun extractId(intentData: String) : String
        fun deserialize(intentData: String) : SeriesModel.SeriesMetaData
    }
}