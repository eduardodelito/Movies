package com.enaz.movies.client.repository

import androidx.lifecycle.LiveData
import com.enaz.movies.client.MoviesApiClient
import com.enaz.movies.client.MoviesApiService
import com.enaz.movies.database.dao.MovieDao
import com.enaz.movies.database.entity.MovieEntity

/**
 * Class that connects data from service and database.
 *
 * Created by eduardo.delito on 5/15/20.
 */
interface MoviesRepository {
    /**
     * Get movie list from local data base.
     */
    fun getMovies(): LiveData<List<MovieEntity>>

    fun getMoviesResponse(): MoviesApiService

    fun insertMovies(list: List<MovieEntity>)

    fun deleteMovies()
}

class MoviesRepositoryImpl(
    private val moviesApiClient: MoviesApiClient,
    private val movieDao: MovieDao) : MoviesRepository {

    override fun getMovies() = movieDao.getMovies()

    override fun getMoviesResponse() = moviesApiClient.getMoviesResponse()

    override fun insertMovies(list: List<MovieEntity>) = movieDao.insertMovies(list)

    override fun deleteMovies() = movieDao.deleteAll()
}
