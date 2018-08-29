package alifyz.com.popseries.database.dao

import alifyz.com.popseries.database.entity.Popular
import alifyz.com.popseries.database.entity.Top
import android.arch.persistence.room.*

@Dao
interface SeriesDao {

    @Query("SELECT * FROM popular")
    fun getAllPopular() : List<Popular>

    @Query("SELECT * FROM top")
    fun getAllTop() : List<Top>

    @Insert
    fun insertPopular(vararg series: Popular)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTop(vararg  series : Top)

    @Delete
    fun delete(vararg  series: Top)
}