package lima.wilquer.moviesearcher

import lima.wilquer.moviesearcher.data.models.movie.Movies

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setProgress(active: Boolean)

        fun showMovies(movies: List<Movies>?)

        fun onErrorMovies(msgError: String)
    }

    interface Presenter : BasePresenter
}