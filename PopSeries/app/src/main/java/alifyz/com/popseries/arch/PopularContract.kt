package alifyz.com.popseries.arch

import alifyz.com.popseries.model.Reviews
import alifyz.com.popseries.model.SeriesModel
import retrofit2.Response

interface PopularContract {

    interface View : BaseView<PopularPresenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showEmptyContent()

        fun showOffline()

        fun showSavedAlert()

        fun setAdapter(response: Response<SeriesModel>?)

        fun showErrorScreen()

    }

    interface Presenter : BasePresenter
}

