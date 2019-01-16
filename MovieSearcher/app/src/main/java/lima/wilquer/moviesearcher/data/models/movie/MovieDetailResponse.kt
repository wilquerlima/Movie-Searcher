package lima.wilquer.moviesearcher.data.models.movie

import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Collections
import lima.wilquer.moviesearcher.data.models.genre.Genres
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Companies
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Countries
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Languages

data class MovieDetailResponse(
        var adult: Boolean?,
        var backdrop_path: String? = null,
        var belongs_to_collection: ArrayList<Collections>? = null,
        var budget: Int?,
        var genres: List<Genres>?,
        var homepage: String? = null,
        var id: Int?,
        var imdb_id: String? = null,
        var original_language: String?,
        var original_title: String?,
        var overview: String? = null,
        var popularity: Double?,
        var poster_path: String? = null,
        var production_companies: ArrayList<Companies>?,
        var production_countries: ArrayList<Countries>?,
        var release_date: String?,
        var revenue: Int?,
        var runtime: Int? = null,
        var spoken_languages: ArrayList<Languages>?,
        var status: String?,
        var tagline: String? = null,
        var title: String?,
        var video: Boolean?,
        var vote_average: Double?,
        var vote_count: Int?

)