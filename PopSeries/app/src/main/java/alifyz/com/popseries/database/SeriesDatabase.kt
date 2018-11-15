package alifyz.com.popseries.database

import alifyz.com.popseries.database.dao.SeriesDao
import alifyz.com.popseries.database.entity.FavoriteEntity
import alifyz.com.popseries.database.entity.PopularEntity
import alifyz.com.popseries.database.entity.TrendingEntity
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(
        FavoriteEntity::class,
        PopularEntity::class,
        TrendingEntity::class), version = 1)

abstract class SeriesDatabase : RoomDatabase() {

    abstract fun SeriesDao() : SeriesDao

    companion object {

        @Volatile
        private var INSTANCE : SeriesDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : SeriesDatabase? {
            if(INSTANCE == null) {

                return INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

            }
            return INSTANCE
        }

        private fun buildDatabase(context : Context) : SeriesDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    SeriesDatabase::class.java,
                    "series.db").build()
        }
    }

}