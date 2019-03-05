package com.alifyz.mvp.mvp

/**
 * No contrato podemos definir todos os métodos que são mandatórios
 * de implementação pelas camadas View e Presenter de cada uma de nossas
 * Activities ou Fragments
 */
interface LoginContract {

    /**
     * Nossa LoginActivity precisa implementar os métodos definidos abaixo
     */
    interface View : BaseView<LoginPresenter> {
        fun displayErrorMessage()
        fun displaySucessToast()
        fun startHomeActivity()
    }

    /**
     * Nosso Presenter precisa implementar os seguintes métodos
     */
    interface Presenter : BasePresenter {
        fun isLoginValid(userName: String, password : String)
    }
}