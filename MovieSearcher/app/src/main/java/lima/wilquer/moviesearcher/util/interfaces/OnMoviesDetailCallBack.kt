package lima.wilquer.moviesearcher.util.interfaces

import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse

interface OnMoviesDetailCallBack {

    fun onSucess(movie: MovieDetailResponse?)

    fun onError(msgError: String)

}