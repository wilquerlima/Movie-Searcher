package lima.wilquer.moviesearcher.data.models.movie

import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Collections
import lima.wilquer.moviesearcher.data.models.genre.Genres
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Companies
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Countries
import lima.wilquer.moviesearcher.data.models.movie.MoviesDetails.Languages

data class MovieDetailResponse(
        var adult: Boolean?,
        var backdrop_path: String?,
        var belongs_to_collection: Collections?,
        var budget: Int?,
        var genres: List<Genres>?,
        var homepage: String?,
        var id: Int?,
        var imdb_id: String?,
        var original_language: String?,
        var original_title: String?,
        var overview: String?,
        var popularity: Double?,
        var poster_path: String?,
        var production_companies: List<Companies>?,
        var production_countries: List<Countries>?,
        var release_date: String?,
        var revenue: Int?,
        var runtime: Int?,
        var spoken_languages: List<Languages>?,
        var status: String?,
        var tagline: String?,
        var title: String?,
        var video: Boolean?,
        var vote_average: Double?,
        var vote_count: Int?

)