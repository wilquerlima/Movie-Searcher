package lima.wilquer.moviesearcher.data.network.genre

import lima.wilquer.moviesearcher.data.models.genre.GenreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET("genre/movie/list?")
    fun getGenre(@Query("api_key") apiKey : String, @Query("language") language: String) : Call<GenreResponse>
}