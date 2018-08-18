package alifyz.com.popseries.home

import alifyz.com.popseries.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
