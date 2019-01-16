package lima.wilquer.moviesearcher.util.interfaces

import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse
import lima.wilquer.moviesearcher.data.models.movie.Movies

interface OnMoviesDetailCallBack {

    fun onSucess(movie : MovieDetailResponse?)

    fun onError(msgError : String)

}