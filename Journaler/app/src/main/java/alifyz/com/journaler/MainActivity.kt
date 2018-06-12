package alifyz.com.journaler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view : View) {
        val myToast = Toast.makeText(this, "test", Toast.LENGTH_LONG);
        myToast.show();
    }
}
