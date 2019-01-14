package lima.wilquer.moviesearcher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
}
