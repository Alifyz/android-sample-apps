package com.alifyz.roomwithlivedata.database.files.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.alifyz.roomwithlivedata.database.files.AppDatabase
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity
import com.alifyz.roomwithlivedata.repository.DesertRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async

class DesertViewModel(application : Application) : AndroidViewModel(application) {

    private var parentJob = Job()

    private val repository : DesertRepository
    val deserts : LiveData<List<DesertEntity>>

    init {
        val dao = AppDatabase.getInstance(application)!!.desertDao()
        repository = DesertRepository(dao)
        deserts = repository.allDeserts
    }

    fun insertDesert(desert : DesertEntity) {
        async {
            repository.insertDesert(desert)
        }
    }
}