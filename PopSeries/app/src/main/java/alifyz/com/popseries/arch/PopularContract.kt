package alifyz.com.popseries.arch

import alifyz.com.popseries.model.SeriesModel
import retrofit2.Response

interface PopularUIContract {

    interface View : BaseView<Presenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showEmptyContent()

        fun showOffline()

        fun showSavedAlert()

        fun setAdapter(response: Response<SeriesModel>?)
    }

    interface Presenter : BasePresenter {
        fun openDetails()
    }
}

