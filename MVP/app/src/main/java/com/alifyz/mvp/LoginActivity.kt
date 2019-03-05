package com.alifyz.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    lateinit var loginInput : TextInputEditText
    lateinit var passwordInput : TextInputEditText
    lateinit var btnLogin : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginInput = findViewById(R.id.et_login)
        passwordInput = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            val login = loginInput.text.toString()
            val password = passwordInput.text.toString()

            if(login.isEmpty() || password.isEmpty()) {
                loginInput.setError("An error occur while trying to log in")
                passwordInput.setError("An error occur while trying to log in")
            }else {
                if(login == AuthUtils.USERNAME && password == AuthUtils.PASSWORD) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }else {
                    loginInput.setError("An error occur while trying to log in")
                    passwordInput.setError("An error occur while trying to log in")
                }
            }
        }
    }
}
