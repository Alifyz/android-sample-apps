package com.alifyz.notesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var dataSet : List<NotesEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.note.text = dataSet.get(position).texto
        holder.priority.text = dataSet.get(position).prioridade
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val note = itemView.findViewById<TextView>(R.id.notes)
        val priority = itemView.findViewById<TextView>(R.id.priority)
    }

    fun setData(newData : List<NotesEntity>) {
        dataSet = newData
        notifyDataSetChanged()
    }

    fun getData(position: Int) : NotesEntity {
        return dataSet.get(position)
    }
}