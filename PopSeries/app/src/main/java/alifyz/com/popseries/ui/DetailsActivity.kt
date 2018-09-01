package alifyz.com.popseries.ui

import alifyz.com.popseries.R
import alifyz.com.popseries.model.PopularModel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val rawJson = intent.getStringExtra("data")
        val builder = GsonBuilder()
        val gson = builder.create()
        val seriesDetail = gson.fromJson(rawJson, PopularModel.Popular::class.java)

        setTitle(seriesDetail.originalName)
    }
}

