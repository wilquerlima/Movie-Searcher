package lima.wilquer.moviesearcher.data.network.movie

import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService{

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id : Int, @Query("api_key") apiKey : String) : Call<MovieResponse>

    @GET("movie/top_rated?")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("language") language : String) : Call<MovieResponse>
}