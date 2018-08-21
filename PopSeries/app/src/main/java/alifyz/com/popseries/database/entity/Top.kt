package alifyz.com.popseries.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "top")
data class Top(
        @PrimaryKey
        var id: Int? = null,
        var originalName: String? = null,
        var name: String? = null,
        var popularity: Double? = null,
        var voteCount: Int? = null,
        var voteAverage: Double? = null,
        var firstAirDate: String? = null,
        var posterPath: String? = null,
        var genreIds: List<Int>? = null,
        var originalLanguage: String? = null,
        var backdropPath: String? = null,
        var overview: String? = null,
        var originCountry: List<String>? = null
)