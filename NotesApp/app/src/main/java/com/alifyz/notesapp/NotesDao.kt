package com.alifyz.notesapp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * from notesTable")
    fun getAllNotes() : List<NotesEntity>

    @Query("SELECT * from notesTable")
    fun getAll() : LiveData<List<NotesEntity>>

    @Insert
    fun insertNote(notes : NotesEntity)

    @Delete
    fun deleteNote(note : NotesEntity)

}