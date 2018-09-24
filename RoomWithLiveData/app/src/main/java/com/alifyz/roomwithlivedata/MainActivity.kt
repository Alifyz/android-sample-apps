package com.alifyz.roomwithlivedata

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity
import com.alifyz.roomwithlivedata.database.files.viewmodel.DesertViewModel
import com.alifyz.roomwithlivedata.recycler.DesertAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10
    private lateinit var desertViewModel : DesertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = DesertAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        //ViewModel
        desertViewModel = ViewModelProviders.of(this).get(DesertViewModel::class.java)
        desertViewModel.deserts.observe(this, Observer {
            deserts -> deserts?.let { adapter.setDeserts(it) }
        })

        fab.setOnClickListener { view ->
           val intentDesert  = Intent(this, NewDesertActivity.javaClass)
            startActivityForResult(intentDesert, REQUEST_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newDesert = DesertEntity(
                    data?.getStringExtra(NewDesertActivity.NAME),
                    data?.getStringExtra(NewDesertActivity.PRICE))

            desertViewModel.insertDesert(newDesert)
        } else {
            toast(R.string.error)
        }
    }
}
