package com.alifyz.notesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_new_note.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NewNoteActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var database : NotesDatabase
    lateinit var currentNote : NotesEntity

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //No need to implement
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //No need to implement
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        selector_value.text = progress.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        setupDatabase()

        selector_value.text = priority_selector.progress.toString()
        priority_selector.setOnSeekBarChangeListener(this)

        //Lógica para adicionar um novo Registro no Banco
        btn_adicionar.setOnClickListener {
            val valor = selector_value.text.toString()
            val texto = et_texto.text.toString()

            currentNote = NotesEntity(texto, valor)

            //Fazendo o Insert em uma Background Thread
            doAsync {
                database.DAO().insertNote(currentNote)

                uiThread {
                    finish() //Finaliza a Activity para Retornar a Tela principal
                }
            }
        }
    }

    private fun setupDatabase() {
        database = NotesDatabase.getInstance(this)
    }
}
