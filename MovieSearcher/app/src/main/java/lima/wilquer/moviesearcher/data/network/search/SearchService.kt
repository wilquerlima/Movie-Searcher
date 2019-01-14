package lima.wilquer.moviesearcher.data.network.search

import retrofit2.http.GET

interface SearchService{

    @GET("search/movie?")
    fun searchMovie()
}