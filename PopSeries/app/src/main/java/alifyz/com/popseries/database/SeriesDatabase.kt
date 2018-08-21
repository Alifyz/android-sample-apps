package alifyz.com.popseries.database

import alifyz.com.popseries.database.dao.SeriesDao
import alifyz.com.popseries.database.entity.Popular
import alifyz.com.popseries.database.entity.Top
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Popular::class, Top::class), version = 1)
abstract class SeriesDatabase : RoomDatabase() {

    abstract fun SeriesDao(): SeriesDao

    companion object {
        private var INSTANCE : SeriesDatabase? = null
        fun getInstance(context: Context) : SeriesDatabase?{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context,
                        SeriesDatabase::class.java,
                        "SeriesDb")
                        .build()
                return INSTANCE
            } else {
                return INSTANCE
            }
        }
    }

}