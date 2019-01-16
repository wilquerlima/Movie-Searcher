package lima.wilquer.moviesearcher.data.network.movie

import lima.wilquer.moviesearcher.data.models.movie.MovieDetailResponse
import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{id}?")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieDetailResponse>

    @GET("movie/popular?")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: String): Call<MovieResponse>
}