package alifyz.com.popseries

import alifyz.com.popseries.model.Series
import alifyz.com.popseries.network.NetworkBuilder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    val series : Series? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = NetworkBuilder().init()

    }
}
