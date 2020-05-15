package com.enaz.movies.client.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enaz.movies.client.MoviesApiClient
import com.enaz.movies.client.model.MoviesResponse
import com.enaz.movies.database.dao.MovieDao
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.util.clientModelToMovieEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by eduardo.delito on 5/15/20.
 */
interface MoviesRepository {
//    suspend fun getMovies(search: String?): MoviesResponse

    val errorMessage: LiveData<Int?>

    val loading: LiveData<Boolean>

    fun searchMovies(search: String?)

    fun getMovies(): LiveData<List<MovieEntity>>

    fun deleteProducts()
}

class MoviesRepositoryImpl(
    private val moviesApiClient: MoviesApiClient,
    private val movieDao: MovieDao
) : MoviesRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<Int?>()
    override val errorMessage: LiveData<Int?> get() = _errorMessage

//    override suspend fun getMovies(search: String?) = moviesApiClient.getMoviesResponse().getMovies(search, COUNTRY, MEDIA)

    override fun getMovies() = movieDao.getMovies()

    override fun searchMovies(search: String?) {
        _loading.postValue(true)
        launch {
            insertMoviesBG(moviesApiClient.getMoviesResponse().getMovies(search, COUNTRY, MEDIA))
        }
    }

    private suspend fun insertMoviesBG(moviesResponse : MoviesResponse) {
        withContext(Dispatchers.IO) {
            try {
                _loading.postValue(false)
                movieDao.deleteAll()
                movieDao.insertMovies(moviesResponse.results.clientModelToMovieEntity())
            } catch (e: Exception) {
                _loading.postValue(false)
                _errorMessage.postValue(null)
            }
        }
    }

    override fun deleteProducts() {
        launch { deleteProductsBG() }
    }

    private suspend fun deleteProductsBG() {
        withContext(Dispatchers.IO) {
            movieDao.deleteAll()
        }
    }

    companion object {
        const val COUNTRY = "AU"
        const val MEDIA = "movie"
    }
}
