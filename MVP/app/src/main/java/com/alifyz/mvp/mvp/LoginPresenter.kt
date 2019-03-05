package com.alifyz.mvp.mvp

import com.alifyz.mvp.AuthUtils

class LoginPresenter(private val view : LoginContract.View) : LoginContract.Presenter {

    /**
     * No Presenter podemos definir um método comum a todas as camadas
     * Aqui podemos fazer o Bind das views necessárias na Activity
     * ou qualquer outra inicialização necessária
     */
    override fun start() {
        view.bindViews()
    }

    /**
     * Principal lógica responsável pelo login e senha do nosso aplicativo
     * Simplificando a separação de camadas do software.
     */
    override fun isLoginValid(userName: String, password: String) {
        if(userName.isEmpty() || password.isEmpty()) {
            view.displayErrorMessage()
        } else {
            if(password == AuthUtils.PASSWORD && userName == AuthUtils.USERNAME) {
                view.displaySucessToast()
                view.startHomeActivity()
            }else {
                view.displayErrorMessage()
            }
        }
    }
}