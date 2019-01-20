package com.alifyz.notesapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlin.concurrent.thread

class TouchHelper(val adapter : Adapter, val context : Context) : ItemTouchHelper.Callback() {

    //Instância do nosso banco de dados
    lateinit var database : NotesDatabase

    override fun getMovementFlags(recyclerView: RecyclerView, viewHOlder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }

    //Não precisamos implementar este método
    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
       return false
    }

    //Sempre que detectaro Swipe, identificar a anotação na posição e deletar do Banco
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
        database = NotesDatabase.getInstance(context)
        thread {
            database.DAO().deleteNote(adapter.getData(viewHolder.adapterPosition))
        }
    }
}