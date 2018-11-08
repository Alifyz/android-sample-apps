package alifyz.com.popseries.arch

interface BaseView<T> {
    fun setPresenter(presenter: T)
}