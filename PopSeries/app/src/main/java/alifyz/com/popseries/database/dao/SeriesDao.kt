package alifyz.com.popseries.database.dao

import alifyz.com.popseries.database.entity.FavoriteEntity
import alifyz.com.popseries.database.entity.PopularEntity
import alifyz.com.popseries.database.entity.TrendingEntity
import android.arch.persistence.room.Query

interface SeriesDao {

    @Query("SELECT * FROM favorite")
    fun getFavoriteSeries() : List<FavoriteEntity>

    @Query("SELECT * FROM popular")
    fun getPopularSeries() : List<PopularEntity>

    @Query("SELECT * FROM trending")
    fun getTrendingSeries() : List<TrendingEntity>


}