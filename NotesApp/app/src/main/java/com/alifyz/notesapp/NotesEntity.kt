package com.alifyz.notesapp

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NotesEntity(
     @PrimaryKey
     @ColumnInfo(name = "texto")
     var texto : String = "",
     var prioridade : String = "")