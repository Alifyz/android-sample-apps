package alifyz.com.popseries.arch

interface PopularUIContract {

    interface View : BaseView<Presenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showEmptyContent()

        fun showOffline()

        fun showSavedAlert()

        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter {
        fun loadDataFromInternet()
    }
}

