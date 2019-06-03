package lima.wilquer.moviesearcher.view.describe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_describe_movie.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse
import lima.wilquer.moviesearcher.util.Constants

class DescribeMovieActivity : AppCompatActivity(), DescribeContract.View {

    override lateinit var presenter: DescribeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_describe_movie)

        val id = intent.extras!!.getInt("id")
        val title = intent.extras!!.getString("title")

        setSupportActionBar(my_toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        DescribePresenter(this, id)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setProgress(active: Boolean) {
        if (active) {
            progress?.visibility = View.VISIBLE
        } else {
            progress?.visibility = View.GONE
        }
    }

    override fun showMovie(movie: MovieDetailResponse?) {
        val url = Constants.URL_IMAGE + Constants.POSTER_SIZE_DETAILS + movie!!.poster_path
        Picasso.with(this).load(url).into(img_desc)
        descricao.visibility = View.VISIBLE

        if (movie.overview.equals("")) {
            txt_desc.text = getString(R.string.noDescription)
        } else txt_desc.text = movie.overview
    }

    override fun onErrorMovie(msgError: String) {
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }
}