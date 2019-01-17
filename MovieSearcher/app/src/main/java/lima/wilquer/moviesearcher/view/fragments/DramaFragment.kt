package lima.wilquer.moviesearcher.view.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_drama.*
import kotlinx.android.synthetic.main.fragment_drama.view.*
import lima.wilquer.moviesearcher.R
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import lima.wilquer.moviesearcher.data.models.movie.Movies
import lima.wilquer.moviesearcher.data.network.api.RetrofitApi
import lima.wilquer.moviesearcher.data.network.movie.MovieService
import lima.wilquer.moviesearcher.util.Constants
import lima.wilquer.moviesearcher.util.adapters.RecycleViewAdapter
import lima.wilquer.moviesearcher.util.interfaces.OnMoviesCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wilqu on 15/01/2019.
 */

class DramaFragment : Fragment(), OnMoviesCallBack {

    var ctx: Context? = null
    var rv: RecyclerView? = null
    val callback: OnMoviesCallBack = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_drama, container, false)

        rv = view.recycleViewDrama
        view.progress?.visibility = View.VISIBLE

        DoAsync(callback).execute()

        return view
    }

    class DoAsync(val callback: OnMoviesCallBack) : AsyncTask<Void, Void, Void>() {


        override fun doInBackground(vararg p0: Void?): Void? {
            val apiService = RetrofitApi(Constants.URL_GERAL).client.create(MovieService::class.java)

            val call = apiService.getPopularMovies(Constants.API_KEY, Constants.LANGUAGE_PT_BR, "1")
            call.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    Log.e("onFailure", t.toString())
                    callback.onError("onError")
                }

                override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>) {
                    //callback para setar o adapter das Recycle Views
                    callback.onSucess(response.body()!!.results)

                }
            })
            return null
        }

    }

    override fun onSucess(movies: List<Movies>?) {
        progress?.visibility = View.GONE
        val moviesList = mutableListOf<Movies>()

        //serve para apenas adicionar a lista de filmes, os filmes que forem correspondentes ao genero escolhido
        for (i in 0 until movies!!.size - 1) {
            for (j in 0 until movies[i].genre_ids.size - 1)
                if (Constants.ID_GENRE_DRAMA == movies[i].genre_ids[j]) {
                    moviesList.add(movies[i])
                    break
                }
        }

        //lib para deixar os cardviews lado a lado
        val layoutManager = FlexboxLayoutManager(ctx)
        layoutManager.flexDirection = FlexDirection.ROW
        rv!!.layoutManager = layoutManager
        rv!!.adapter = RecycleViewAdapter(moviesList, ctx!!)
    }

    override fun onError(msgError: String) {
        progress?.visibility = View.GONE
        Toast.makeText(context, msgError, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ctx = context
    }
}