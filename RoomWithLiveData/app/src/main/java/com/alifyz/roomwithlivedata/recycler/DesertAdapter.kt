package com.alifyz.roomwithlivedata.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alifyz.roomwithlivedata.R
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity


class DesertAdapter internal constructor(val context : Context) : RecyclerView.Adapter<DesertAdapter.DesertViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var deserts = emptyList<DesertEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesertViewHolder {
        return DesertViewHolder(inflater.inflate(R.layout.desert_item, parent, false))
    }

    override fun getItemCount(): Int {
        return deserts.size
    }

    override fun onBindViewHolder(holder: DesertViewHolder, position: Int) {
        val current = deserts[position]
        holder.desertName.text = current.mDesertName
        holder.desertPrice.text = current.mDesertPrice.toString()
    }

    inner class  DesertViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val desertName : TextView = itemView.findViewById(R.id.desert_name)
        val desertPrice : TextView = itemView.findViewById(R.id.desert_price)
    }

    //Refresh the RecyclerView Items
    internal fun setDeserts(deserts : List<DesertEntity>) {
        this.deserts = deserts
        notifyDataSetChanged()
    }

}