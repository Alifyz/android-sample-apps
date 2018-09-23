package com.alifyz.roomwithlivedata.database.files.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity

interface DesertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDesert(desert : DesertEntity)

    @Query("SELECT * from deserts ORDER BY price ASC")
    fun getAllDeserts() : List<DesertEntity>

    @Query("DELETE from deserts")
    fun deleteAllDeserts()

}