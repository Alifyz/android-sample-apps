package com.alifyz.ankodemo

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_toast.setOnClickListener {
            toast("Mensagem exemplo")
        }

        btn_dialog.setOnClickListener {

            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Atenção!")
            alertBuilder.setMessage("Mensagem de Exemplo de Alerta!!")
            alertBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { _, which ->
                toast("Ação Positiva")
            })
            alertBuilder.setNegativeButton("Cancelar", DialogInterface.OnClickListener{_, which ->
                toast("Ação Negativa")
            })

            val alert = alertBuilder.create()
            alert.show()
        }
    }
}
