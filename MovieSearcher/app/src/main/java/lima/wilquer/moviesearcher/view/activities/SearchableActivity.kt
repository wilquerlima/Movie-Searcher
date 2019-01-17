package lima.wilquer.moviesearcher.view.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_searchable.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.search.SearchService
import lima.wilquer.moviesearcher.util.Constants
import lima.wilquer.moviesearcher.util.adapters.RecycleViewAdapter
import lima.wilquer.moviesearcher.util.interfaces.OnMoviesCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchableActivity : AppCompatActivity(), OnMoviesCallBack {

    val callback: OnMoviesCallBack = this
    var rv: RecyclerView? = null
    lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        setSupportActionBar(my_toolbar)
        supportActionBar?.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        rv = recycleViewSearch
        progress?.visibility = View.VISIBLE
        handleIntent(intent)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            query = intent.getStringExtra(SearchManager.QUERY)

            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(SearchService::class.java)

            val call = apiService.getSearchedMovie(Constants.API_KEY, Constants.LANGUAGE_PT_BR, query)
            call.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    Log.e("onFailure", t.toString())
                    callback.onError("onError")
                }

                override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>) {
                    callback.onSucess(response.body()!!.results)
                }
            })
        }
    }

    override fun onSucess(movies: List<Movies>?) {
        progress?.visibility = View.GONE
        supportActionBar?.title = query

        //lib para deixar os cardviews lado a lado
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        rv!!.layoutManager = layoutManager
        rv!!.adapter = RecycleViewAdapter(movies, this)
    }

    override fun onError(msgError: String) {
        progress?.visibility = View.GONE
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }
}