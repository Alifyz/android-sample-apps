package com.alifyz.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alifyz.mvp.mvp.LoginContract
import com.alifyz.mvp.mvp.LoginPresenter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter : LoginPresenter

    lateinit var loginInput : TextInputEditText
    lateinit var passwordInput : TextInputEditText
    lateinit var btnLogin : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Bind do Presenter com a nossa View
        presenter = LoginPresenter(this)

        presenter.start() // Chama o método responsável por dizer a View o que deve ser inicializado.

        btnLogin.setOnClickListener {
          //Comunica e Transfere a responsabilidade do Login para o Presenter
          //Que então irá validar se o Login é válido ou não.
          presenter.isLoginValid(loginInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun displayErrorMessage() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        loginInput.error = "Login Failed!"
        passwordInput.error = "Login Failed!"
    }

    override fun displaySucessToast() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
    }

    override fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun bindViews() {
        loginInput = findViewById(R.id.et_login)
        passwordInput = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.buttonLogin)
    }
}
