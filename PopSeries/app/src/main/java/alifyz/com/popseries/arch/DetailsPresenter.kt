package alifyz.com.popseries.arch

class DetailsPresenter( view : DetailsContract.View) : DetailsContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {

    }
}