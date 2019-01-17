package lima.wilquer.moviesearcher.view.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.Toast
import lima.wilquer.moviesearcher.util.adapters.ViewPagerAdapter
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.genre.Genres
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.data.network.movie.MovieService
import lima.wilquer.moviesearcher.util.interfaces.OnMoviesCallBack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar?.title = getString(R.string.title_movie)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter
        tab.setupWithViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Pesquisar...")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(this@MainActivity,p0,Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, SearchableActivity::class.java)
                intent.putExtra(SearchManager.QUERY, p0)
                intent.setAction(Intent.ACTION_SEARCH)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        // Associate searchable configuration with the SearchView
        /*val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }*/

        return true
    }
}
