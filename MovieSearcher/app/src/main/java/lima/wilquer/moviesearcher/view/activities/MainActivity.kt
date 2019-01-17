package lima.wilquer.moviesearcher.view.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.util.adapters.ViewPagerAdapter

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
        searchView.queryHint = getString(R.string.searchview_hint)

        //listener para o searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //listener para quando o clique no teclado for executado
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Toast.makeText(this@MainActivity,p0,Toast.LENGTH_SHORT).show()
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

        return true
    }
}
