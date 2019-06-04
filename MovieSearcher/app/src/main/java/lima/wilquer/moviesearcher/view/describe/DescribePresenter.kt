package lima.wilquer.moviesearcher.view.describe

import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.movie.MovieService
import lima.wilquer.moviesearcher.util.Constants
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescribePresenter(val describeView: DescribeContract.View, val idMovie: Int) : DescribeContract.Presenter {

    init {
        describeView.presenter = this
    }

    override fun start() {
        loadMovie()
    }

    fun loadMovie() {
        describeView.setProgress(true)

        doAsync {

            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(MovieService::class.java)

            val call = apiService.getMovieDetails(idMovie, Constants.API_KEY, Constants.LANGUAGE_PT_BR)
            call.enqueue(object : Callback<MovieDetailResponse> {
                override fun onFailure(call: Call<MovieDetailResponse>?, t: Throwable?) {
                    describeView.setProgress(false)
                    describeView.onErrorMovie("onError")
                }

                override fun onResponse(call: Call<MovieDetailResponse>?, response: Response<MovieDetailResponse>?) {
                    describeView.setProgress(false)
                    describeView.showMovie(response?.body())
                }
            })
        }
    }

}