package lima.wilquer.moviesearcher.view.search

import lima.wilquer.moviesearcher.BaseContract
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.search.SearchService
import lima.wilquer.moviesearcher.util.Constants
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(val searchView: BaseContract.View, val query: String) : BaseContract.Presenter {
    init {
        searchView.presenter = this
    }

    override fun start() {
        loadMovies()
    }

    fun loadMovies() {
        doAsync {
            searchView.setProgress(true)

            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(SearchService::class.java)

            val call = apiService.getSearchedMovie(Constants.API_KEY, Constants.LANGUAGE_PT_BR, query)
            call.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    searchView.setProgress(false)
                    searchView.onErrorMovies("onError")
                }

                override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>) {
                    searchView.setProgress(false)
                    searchView.showMovies(response.body()!!.results)
                }
            })
        }
    }
}