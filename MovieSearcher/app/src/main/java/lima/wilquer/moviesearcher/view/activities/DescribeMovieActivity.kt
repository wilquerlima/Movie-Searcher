package lima.wilquer.moviesearcher.view.activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_describe_movie.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.movie.MovieService
import lima.wilquer.moviesearcher.util.Constants
import lima.wilquer.moviesearcher.util.interfaces.OnMoviesDetailCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescribeMovieActivity : AppCompatActivity(), OnMoviesDetailCallBack {

    val callback: OnMoviesDetailCallBack = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_describe_movie)

        val id = intent.extras.getInt("id")
        val title = intent.extras.getString("title")

        setSupportActionBar(my_toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        DoAsync(callback, id).execute()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class DoAsync(val callback: OnMoviesDetailCallBack, val idMovie: Int) : AsyncTask<Void, Void, Void>() {


        override fun doInBackground(vararg p0: Void?): Void? {
            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(MovieService::class.java)

            val call = apiService.getMovieDetails(idMovie, Constants.API_KEY, Constants.LANGUAGE_PT_BR)
            call.enqueue(object : Callback<MovieDetailResponse> {
                override fun onFailure(call: Call<MovieDetailResponse>?, t: Throwable?) {
                    callback.onError("onError")
                }

                override fun onResponse(call: Call<MovieDetailResponse>?, response: Response<MovieDetailResponse>?) {
                    callback.onSucess(response!!.body())
                }
            })
            return null
        }

    }

    override fun onSucess(movie: MovieDetailResponse?) {
        //Toast.makeText(this, "onResponse", Toast.LENGTH_SHORT).show()
        val url = Constants.URL_IMAGE + Constants.POSTER_SIZE_DETAILS + movie!!.poster_path
        Picasso.with(this).
                load(url).
                into(img_desc)
        descricao.visibility = View.VISIBLE
        txt_desc.text = movie.overview
    }

    override fun onError(msgError: String) {
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }
}