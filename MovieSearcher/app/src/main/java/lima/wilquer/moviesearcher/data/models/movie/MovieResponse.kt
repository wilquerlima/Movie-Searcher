package lima.wilquer.moviesearcher.data.models.movie

data class MovieResponse(
        var page: Int?,
        var total_results: Int?,
        var results: List<Movies>?
)