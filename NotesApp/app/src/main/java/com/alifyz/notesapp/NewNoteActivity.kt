package com.alifyz.notesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

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

        priority_selector.setOnSeekBarChangeListener(this)
    }
}
