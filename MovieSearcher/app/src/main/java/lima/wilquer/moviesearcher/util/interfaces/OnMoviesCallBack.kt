package lima.wilquer.moviesearcher.util.interfaces

import lima.wilquer.moviesearcher.data.models.movie.Movies

interface OnMoviesCallBack {

    fun onSucess(movies : List<Movies>?)

    fun onError(msgError : String)

}