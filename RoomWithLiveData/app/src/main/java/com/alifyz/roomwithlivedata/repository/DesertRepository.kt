package com.alifyz.roomwithlivedata.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.alifyz.roomwithlivedata.database.files.AppDatabase
import com.alifyz.roomwithlivedata.database.files.dao.DesertDao
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity
import org.jetbrains.anko.doAsync

class DesertRepository(private val dao : DesertDao) {

    val allDeserts : LiveData<List<DesertEntity>> = dao.getAllDeserts()

    @WorkerThread
    suspend fun insertDesert(desert : DesertEntity) {
        dao.insertDesert(desert)
    }

}