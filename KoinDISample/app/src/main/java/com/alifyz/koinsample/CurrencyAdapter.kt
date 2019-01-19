package com.alifyz.koinsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alifyz.koinsample.model.Currency

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    val currencies : List<Currency> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
       return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return currencies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.name.text = currencies.get(position).name
       holder.symbol.text = currencies.get(position).symbol
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val symbol = itemView.findViewById<TextView>(R.id.symbol)
    }
}