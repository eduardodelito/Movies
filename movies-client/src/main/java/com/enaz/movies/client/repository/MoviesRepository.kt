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

    /**
     * Get Instance call for retrofit service.
     */
    fun getMoviesResponse(): MoviesApiService

    /**
     *  Insert movies in the database.
     *  @param list of movies
     */
    fun insertMovies(list: List<MovieEntity>)

    /**
     *  Delete movies in the database.
     */
    fun deleteMovies()
}

class MoviesRepositoryImpl(
    private val moviesApiClient: MoviesApiClient,
    private val movieDao: MovieDao) : MoviesRepository {

    /**
     * Get movie list from local data base.
     */
    override fun getMovies() = movieDao.getMovies()

    /**
     * Get Instance call for retrofit service.
     */
    override fun getMoviesResponse() = moviesApiClient.getMoviesResponse()

    /**
     *  Insert movies in the database.
     *  @param list of movies
     */
    override fun insertMovies(list: List<MovieEntity>) = movieDao.insertMovies(list)

    /**
     *  Delete movies in the database.
     */
    override fun deleteMovies() = movieDao.deleteAll()
}
