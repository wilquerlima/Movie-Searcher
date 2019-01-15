package lima.wilquer.moviesearcher.ui.activities

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.ProgressBar
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.genre.GenreResponse
import lima.wilquer.moviesearcher.data.models.genre.Genres
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.genre.GenreService
import lima.wilquer.moviesearcher.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.title_movie)

        val progress = findViewById<ProgressBar>(R.id.progress)
        val handle = Handler(Handler.Callback{

        })
        val apiService = RetrofitApi(Constants.URL_GERAL).client.create(GenreService::class.java)

        val call = apiService.getGenre(Constants.API_KEY, Constants.LANGUAGE_PT_BR)

        call.enqueue(object : Callback<GenreResponse>{
            override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GenreResponse>?, response: Response<GenreResponse>) {
                response.body()?.let{
                    val listGenres : List<Genres>? = it.genres
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }
}
