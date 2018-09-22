package login

class LoginPresenter(loginView: LoginView) {

    var currentView : LoginView? = null

    fun attachView (view : LoginView) {
        currentView = view
    }

}