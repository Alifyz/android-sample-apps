package com.alifyz.roomwithlivedata.database.files.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "deserts")
data class DesertEntity(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val mDesertName : String?,
        @ColumnInfo(name = "price")
        val mDesertPrice : Double?
)