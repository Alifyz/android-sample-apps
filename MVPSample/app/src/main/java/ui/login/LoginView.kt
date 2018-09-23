package ui.login

interface LoginView {
    fun displayErrorMessage()
    fun navigateToHomeScreen()
    fun showProgress()
    fun dismissProgress()
}