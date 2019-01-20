package com.alifyz.notesapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(NotesEntity::class), version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun DAO() : NotesDao

    companion object {
        private var INSTANCE : NotesDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : NotesDatabase {

            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "database.db").build()
            }
            return INSTANCE as NotesDatabase
        }
    }
}