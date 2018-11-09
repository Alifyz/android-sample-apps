package alifyz.com.popseries.arch

import alifyz.com.popseries.model.SeriesModel
import retrofit2.Response

interface TrendingContract {

    interface View : BaseView<TrendingPresenter>{
        fun setLoadingIndicator(active: Boolean)

        fun showEmptyContent()

        fun showOffline()

        fun showSavedAlert()

        fun setAdapter(response: Response<SeriesModel>?)

        fun showErrorScreen()
    }

    interface Presenter : BasePresenter
}