package alifyz.com.popseries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SeriesDetailModel {

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("created_by")
    @Expose
    var createdBy: List<CreatedBy>? = null
    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int>? = null
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("in_production")
    @Expose
    var inProduction: Boolean? = null
    @SerializedName("languages")
    @Expose
    var languages: List<String>? = null
    @SerializedName("last_air_date")
    @Expose
    var lastAirDate: String? = null
    @SerializedName("last_episode_to_air")
    @Expose
    var lastEpisodeToAir: LastEpisodeToAir? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("next_episode_to_air")
    @Expose
    var nextEpisodeToAir: Any? = null
    @SerializedName("networks")
    @Expose
    var networks: List<Network>? = null
    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int? = null
    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_name")
    @Expose
    var originalName: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("seasons")
    @Expose
    var seasons: List<Season>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("videos")
    @Expose
    var videos: Videos? = null
    @SerializedName("images")
    @Expose
    var images: Images? = null
    @SerializedName("reviews")
    @Expose
    var reviews: Reviews? = null
    @SerializedName("credits")
    @Expose
    var credits: Credits? = null
    @SerializedName("recommendations")
    @Expose
    var recommendations: Recommendations? = null

}

class Backdrop {

    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double? = null
    @SerializedName("file_path")
    @Expose
    var filePath: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

}


class Cast {

    @SerializedName("character")
    @Expose
    var character: String? = null
    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("gender")
    @Expose
    var gender: Int? = null
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null
    @SerializedName("order")
    @Expose
    var order: Int? = null

}

class CreatedBy {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("gender")
    @Expose
    var gender: Int? = null
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null

}

class Credits {

    @SerializedName("cast")
    @Expose
    var cast: List<Cast>? = null
    @SerializedName("crew")
    @Expose
    var crew: List<Any>? = null

}





class Genre {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}


class Images {

    @SerializedName("backdrops")
    @Expose
    var backdrops: List<Backdrop>? = null
    @SerializedName("posters")
    @Expose
    var posters: List<Poster>? = null

}

class LastEpisodeToAir {

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null
    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("production_code")
    @Expose
    var productionCode: Any? = null
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null
    @SerializedName("still_path")
    @Expose
    var stillPath: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

}


class Logo {

    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double? = null

}


class Network {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

}


class Network_ {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo")
    @Expose
    var logo: Logo? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

}


class Poster {

    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double? = null
    @SerializedName("file_path")
    @Expose
    var filePath: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

}


class ProductionCompany {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: Any? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

}

class Recommendations {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result_>? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

}

class Result {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("site")
    @Expose
    var site: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}


class Result_ {

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_name")
    @Expose
    var originalName: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("networks")
    @Expose
    var networks: List<Network_>? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null

}

class Reviews {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Any>? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

}


class Season {

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null
    @SerializedName("episode_count")
    @Expose
    var episodeCount: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: Any? = null
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null

}

class Videos {

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

}