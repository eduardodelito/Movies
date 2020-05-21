package com.enaz.movies.client

import com.enaz.movies.client.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service to get and query movies.
 *
 * Created by eduardo.delito on 5/15/20.
 */
interface MoviesApiService {
    @GET("search/")
    suspend fun getMovies(
        @Query("term") term: String?,
        @Query("country") country: String,
        @Query("media") media: String
    ): MoviesResponse
}
