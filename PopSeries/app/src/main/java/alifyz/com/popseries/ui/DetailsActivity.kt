package alifyz.com.popseries.ui

import alifyz.com.popseries.R
import alifyz.com.popseries.model.PopularModel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val rawJson = intent.getStringExtra("data")
        val builder = GsonBuilder()
        val gson = builder.create()
        val seriesDetail = gson.fromJson(rawJson, PopularModel.Popular::class.java)

        val posterImage = findViewById<ImageView>(R.id.poster)
        val posterUrl = getString(R.string.original_path)
                .plus(seriesDetail?.backdropPath)


        val actionBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(actionBar)
        Glide.with(this)
                .load(posterUrl)
                .into(posterImage)


        rating_count.text = setRateCount(seriesDetail)
        rating.rating = setRate(seriesDetail.voteAverage)
        series_title.text = seriesDetail.originalName


        //Transparent StatusBar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }



    fun setRateCount(seriesDetail: PopularModel.Popular) =
            seriesDetail.voteCount!!.toString().plus(" Reviews")

    fun setRate(voteAverage: Double?): Float {
        val vote = voteAverage?.div(10)?.times(5)
        return vote!!.toFloat()
    }


}

