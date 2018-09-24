package com.alifyz.roomwithlivedata.database.files

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.alifyz.roomwithlivedata.database.files.dao.DesertDao
import com.alifyz.roomwithlivedata.database.files.entity.DesertEntity

@Database(entities = arrayOf(DesertEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase(){

    //Database Dao
    abstract fun desertDao() : DesertDao

    //Static Creation of the Local Database
    companion object {
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context : Context) : AppDatabase {

            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "database.db").build()

                return INSTANCE as AppDatabase
            } else {
                return INSTANCE as AppDatabase
            }
        }
    }
}