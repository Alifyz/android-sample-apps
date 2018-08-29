package alifyz.com.popseries.adapter

import alifyz.com.popseries.R
import alifyz.com.popseries.model.PopularModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SeriesAdapter(val context: Context, val dataSet: PopularModel) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val year = view.findViewById<TextView>(R.id.year)
        val poster = view.findViewById<ImageView>(R.id.poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_series, parent, false))
    }

    override fun getItemCount(): Int {
       return dataSet.results!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentShow = dataSet.results?.get(position)

        val posterUrl = context
                .getString(R.string.base_url_path)
                .plus(currentShow?.posterPath)

        holder.title.text = currentShow?.name
        holder.year.text = currentShow?.firstAirDate

        Glide
                .with(context)
                .load(posterUrl)
                .into(holder.poster)

    }
}

