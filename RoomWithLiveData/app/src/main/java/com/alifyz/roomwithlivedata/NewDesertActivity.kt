package com.alifyz.roomwithlivedata

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_desert.*

class NewDesertActivity : AppCompatActivity() {

    private lateinit var editName : EditText
    private lateinit var editPrice : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_desert)


        //Intent Result
        val repplyIntent = Intent()


        button_save.setOnClickListener{
            if(TextUtils.isEmpty(desert_name.text) || TextUtils.isEmpty(desert_price.text)) {
                setResult(Activity.RESULT_CANCELED, repplyIntent)
            } else {
               val desertName = desert_name.text.toString()
               val desertPrice = desert_price.text.toString()

                repplyIntent.putExtra(NAME,  desertName)
                repplyIntent.putExtra(PRICE, desertPrice)

               setResult(Activity.RESULT_OK, repplyIntent)
            }
            finish()
        }
    }

    companion object {
        const val NAME = "name"
        const val PRICE = "price"
    }
}
