package com.alifyz.ankodemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_toast.setOnClickListener {
            toast("Mensagem exemplo")
        }

        btn_dialog.setOnClickListener {
            alert("Mensagem de Alerta") {
                titleResource = R.string.title
                yesButton { toast("Mensagem Positiva")}
                noButton { toast("Mensagem Negativa") }
            }.show()
        }

        btn_activity.setOnClickListener {
            startActivity(intentFor<OtherActivity>("id" to 10).clearTop())
        }

        btn_call.setOnClickListener {
           //Realizar uma Chamada
            makeCall("+666")
        }

        btn_sms.setOnClickListener {
            //Enviar um SMS
           sendSMS("+666", "Hello, it's me! :)")
        }

        btn_browse.setOnClickListener {
            //Navegar pela Internet
            browse("http://www.alifyz.com")
        }

        btn_email.setOnClickListener {
            //Enviar um E-mail
            email("alifyz@gmail.com","Assunto do E-mail", "Olá, texto padrão de E-mail")
        }
    }
}
