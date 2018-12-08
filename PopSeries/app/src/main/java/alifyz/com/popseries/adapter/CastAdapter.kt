package alifyz.com.popseries.adapter

import alifyz.com.popseries.R
import alifyz.com.popseries.model.Credits
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class CastAdapter (val context : Context, val dataSet : Credits) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_details_crew, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.cast!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentShow = dataSet.cast?.get(position)?.profilePath
        val posterUrl = context
                .getString(R.string.base_url_path)
                .plus(currentShow)

        Glide
                .with(context)
                .load(posterUrl)
                .into(holder.profilePicture)

        holder.profileName.text = dataSet.cast?.get(position)?.name
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val profilePicture = view.findViewById<ImageView>(R.id.profile_picture)
        val profileName = view.findViewById<TextView>(R.id.profile_name)
    }
}