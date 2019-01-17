package lima.wilquer.moviesearcher.data.network.search

import lima.wilquer.moviesearcher.data.models.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/movie?")
    fun getSearchedMovie(@Query("api_key") apiKey: String, @Query("language") language: String,
                    @Query("query") query: String): Call<MovieResponse>
}