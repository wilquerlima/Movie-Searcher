package lima.wilquer.moviesearcher.view.describe

import lima.wilquer.moviesearcher.BasePresenter
import lima.wilquer.moviesearcher.BaseView
import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse

interface DescribeContract {

    interface View : BaseView<Presenter> {
        fun setProgress(active: Boolean)

        fun showMovie(movie: MovieDetailResponse?)

        fun onErrorMovie(msgError: String)
    }

    interface Presenter : BasePresenter
}