# Alarm Manager API - Sample

# TL;DR

This project aims to use the Anko Library to demonstrate the powerful extensions and to serve as a code sample for the following
[article](https://medium.com/@alifyzfpires/anko-library-dicas-para-agilizar-o-desenvolvimento-android-parte-1-457afe958844)

```kotlin
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
```


# Pre-requisites

  - Android Studio 2.3+
  - Android SDK Build Tools 23+
  - Target SDK (19) - Most of them. 
 
# Contact Info

- Email: alifyz@outlook.com
- Twitter: @AlifyzPires


License
----

MIT

Copyright - 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

