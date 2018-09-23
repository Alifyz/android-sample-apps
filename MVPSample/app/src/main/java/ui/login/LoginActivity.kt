package ui.login

import alifyz.com.mvpsample.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoginActivity : AppCompatActivity(), LoginView {

    override fun showProgress() {
        TODO("not implemented")
    }

    override fun dismissProgress() {
        TODO("not implemented")
    }

    override fun displayErrorMessage() {
        TODO("not implemented")
    }

    override fun navigateToHomeScreen() {
        TODO("not implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}