package alifyz.com.popseries.ui

import alifyz.com.popseries.R
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.transition.Transition
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val actionBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(actionBar)
        startFragment()
    }

    private fun startFragment() {
        val homeFragment = PopularSeriesFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment, homeFragment)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bottom_popular -> {
               val popularFragment = PopularSeriesFragment()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, popularFragment)
                       .commit()
            }
            R.id.bottom_top -> {
               val topFragment = TrendingSeriesFragment()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, topFragment)
                       .commit()
            }
            R.id.bottom_fav -> {
               val favoriteFragment = FavoriteSeriesFragment()
               supportFragmentManager.beginTransaction()
                       .replace(R.id.fragment, favoriteFragment)
                       .commit()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
