package ui.login

class LoginPresenter(loginView: LoginView) {

    var currentView : LoginView? = null

    fun attachView (view : LoginView) {
        if(currentView == null) {
            currentView = view
        }
    }

    fun dettachView() {
        currentView = null
    }
}