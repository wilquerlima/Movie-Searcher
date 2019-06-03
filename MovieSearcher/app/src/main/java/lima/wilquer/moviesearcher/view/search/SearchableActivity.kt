package lima.wilquer.moviesearcher.view.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_searchable.*
import lima.wilquer.moviesearcher.BaseContract
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.util.adapters.RecycleViewAdapter

class SearchableActivity : AppCompatActivity(), BaseContract.View {

    override lateinit var presenter: BaseContract.Presenter
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
            SearchPresenter(this, query)
            presenter.start()
        }
    }

    override fun setProgress(active: Boolean) {
        if (active){
            progress?.visibility = View.VISIBLE
        } else {
            progress?.visibility = View.GONE
        }
    }

    override fun showMovies(movies: List<Movies>?) {
        supportActionBar?.title = query

        //lib para deixar os cardviews lado a lado
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        rv!!.layoutManager = layoutManager
        rv!!.adapter = RecycleViewAdapter(movies, this)
    }

    override fun onErrorMovies(msgError: String) {
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }
}