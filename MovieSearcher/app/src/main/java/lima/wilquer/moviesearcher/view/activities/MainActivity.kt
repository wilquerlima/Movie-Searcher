package lima.wilquer.moviesearcher.view.activities

import android.app.SearchManager
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.Toast
import lima.wilquer.moviesearcher.ViewPagerAdapter
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.genre.GenreResponse
import lima.wilquer.moviesearcher.data.models.genre.Genres
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.genre.GenreService
import lima.wilquer.moviesearcher.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName
    val handler: Handler = Handler()
    var listGenres: List<Genres>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar!!.title = getString(R.string.title_movie)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter

        tab.setupWithViewPager(viewpager)


        //progress = findViewById<ProgressBar>(R.id.progress)
        //progress?.visibility = View.VISIBLE

        DoAsync(this@MainActivity).execute()
    }

    class DoAsync(context: Context) : AsyncTask<Void,Void,Void>(){

        var ctx = context

        override fun doInBackground(vararg p0: Void?): Void? {
            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(GenreService::class.java)

            val call = apiService.getGenre(Constants.API_KEY, Constants.LANGUAGE_PT_BR)

            call.enqueue(object : Callback<GenreResponse>{
                override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
                    Log.e("onFailure", t.toString())
                }

                override fun onResponse(call: Call<GenreResponse>?, response: Response<GenreResponse>) {
                    //progress.visibility = View.GONE
                    //listGenres = response.body()?.genres
                    response.body()?.let{
                        //ctx.listGenres  = it.genres
                        Toast.makeText(ctx,"onResponse",Toast.LENGTH_SHORT).show()
                    }
                }
            })
            return null
        }

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
