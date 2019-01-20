package com.alifyz.notesapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Alifyz Pires
 * @date 01/20/2019
 */
class MainActivity : AppCompatActivity() {

    //Instância do Banco de Dados que iremos usar
    lateinit var database : NotesDatabase

    //Nosso Adapter customizado para exibição das anotações
    val adapter = Adapter()


    lateinit var notesList : LiveData<List<NotesEntity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDatabase()
        setupRecyclerView()

        notesList.observe(this, Observer {
            it?.let { lista -> adapter.setData(lista) }
        })

        fab_new_note.setOnClickListener {
            val newNoteActivity = Intent(this, NewNoteActivity::class.java)
            startActivity(newNoteActivity)
        }
    }

    //Criação do Banco de Dados
    fun setupDatabase() {
        database = NotesDatabase.getInstance(this)
        notesList = database.DAO().getAll()
    }

    //Configuração do RecyclerView
    fun setupRecyclerView() {
        val touchHelper = ItemTouchHelper(TouchHelper(adapter, this))
        touchHelper.attachToRecyclerView(recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

}
