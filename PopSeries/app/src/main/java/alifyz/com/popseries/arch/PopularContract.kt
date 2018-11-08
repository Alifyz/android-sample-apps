package alifyz.com.popseries.arch

interface PopularUIContract {

    interface View : BaseView<Presenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showMissingTask()

        fun hideTitle()

        fun showTitle(title: String)

        fun hideDescription()

        fun showTaskDeleted()

        fun showTaskMarkedComplete()

        fun showTaskMarkedActive()

        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter {
        fun loadDataFromInternet()
    }
}

