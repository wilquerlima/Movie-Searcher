package lima.wilquer.moviesearcher.view.ficcao

import lima.wilquer.moviesearcher.HomeContract
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.movie.MovieService
import lima.wilquer.moviesearcher.util.Constants
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FiccaoPresenter(val homeView: HomeContract.View) : HomeContract.Presenter {

    init {
        homeView.presenter = this
    }

    override fun start() {
        loadActionMovies()
    }

    private fun loadActionMovies() {
        homeView.setProgress(true)

        doAsync {
            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(MovieService::class.java)

            val call = apiService.getPopularMovies(Constants.API_KEY, Constants.LANGUAGE_PT_BR, "1")
            call.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    homeView.setProgress(false)
                    homeView.onErrorMovies("onError")
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    homeView.setProgress(false)
                    homeView.showMovies(response.body()!!.results)
                }

            })
        }
    }
}