package alifyz.com.popseries.adapter

import alifyz.com.popseries.R
import alifyz.com.popseries.model.Reviews
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.internal.LinkedTreeMap

class CommentAdapter(val context : Context, val dataSet : Reviews ) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
        return CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_details_comment, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.results?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: CommentViewHolder, position: Int) {
        val currentComment = dataSet.results?.get(position) as LinkedTreeMap<String, String>
        viewHolder.author.text = currentComment.get("author")
        viewHolder.comment.text = currentComment.get("content")
    }

    inner class CommentViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val author = view.findViewById<TextView>(R.id.comment_author)
        val comment = view.findViewById<TextView>(R.id.comment_text)
    }
}