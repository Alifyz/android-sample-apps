package com.alifyz.roomwithlivedata.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.alifyz.roomwithlivedata.database.files.AppDatabase
import com.alifyz.roomwithlivedata.database.files.dao.DesertDao
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity
import org.jetbrains.anko.doAsync

class DesertRepository(application : Application) {

    var mDesertDao : DesertDao? = null
    var mAllDeserts : LiveData<List<DesertEntity>>? = null
    var localDatabase : AppDatabase

    init {
        val roomDatabase = AppDatabase.getInstance(application)
        localDatabase = roomDatabase
        mDesertDao = roomDatabase.desertDao()
        mAllDeserts = roomDatabase.desertDao().getAllDeserts()
    }

    fun addDesert(newDesert : DesertEntity) {
        doAsync {
            localDatabase.desertDao().insertDesert(newDesert)
        }
    }
}