package com.alifyz.roomwithlivedata.repository


import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.alifyz.roomwithlivedata.database.files.dao.DesertDao
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity

class DesertRepository(private val dao : DesertDao) {

    val allDeserts : LiveData<List<DesertEntity>> = dao.getAllDeserts()

    @WorkerThread
    suspend fun insertDesert(desert : DesertEntity) {
        dao.insertDesert(desert)
    }

}