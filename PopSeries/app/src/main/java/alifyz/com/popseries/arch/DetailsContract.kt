package alifyz.com.popseries.arch

interface DetailsContract {
    interface View : BaseView<Presenter> {
        fun setDeta()

    }
    interface Presenter : BasePresenter
}