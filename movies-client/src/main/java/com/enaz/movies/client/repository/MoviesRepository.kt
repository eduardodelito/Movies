package com.enaz.movies.client.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enaz.movies.client.MoviesApiClient
import com.enaz.movies.client.R
import com.enaz.movies.client.model.MoviesResponse
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import com.enaz.movies.database.dao.MovieDao
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.util.clientModelToMovieEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Class that connects data from service and database.
 *
 * Created by eduardo.delito on 5/15/20.
 */
interface MoviesRepository {
    /**
     * LiveData for the recent search.
     */
    val recentSearch: LiveData<Pair<Int, String>?>

    /**
     * LiveData for the error banner.
     */
    val errorBanner: LiveData<Pair<Boolean, String>?>

    /**
     * LiveData for showing the loading indicator.
     */
    val loading: LiveData<Boolean>

    /**
     * Search movies based on the query string value.
     * @param search string to search
     */
    fun searchMovies(search: String)

    /**
     * Get movie list from local data base.
     */
    fun getMovies(): LiveData<List<MovieEntity>>

    /**
     * Get the recent search.
     */
    fun getRecent()

    /**
     * Reset error banner.
     */
    fun resetBanner()
}

class MoviesRepositoryImpl(
    private val moviesApiClient: MoviesApiClient,
    private val movieDao: MovieDao,
    private val sharedPreferencesManager: SharedPreferencesManager
) : MoviesRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean> get() = _loading

    private val _recentSearch = MutableLiveData<Pair<Int, String>?>()
    override val recentSearch: LiveData<Pair<Int, String>?> get() = _recentSearch

    private val _errorBanner = MutableLiveData<Pair<Boolean, String>?>()
    override val errorBanner: LiveData<Pair<Boolean, String>?> get() = _errorBanner

    /**
     * Get movie list from local data base.
     */
    override fun getMovies() = movieDao.getMovies()

    /**
     * Search movies based on the query string value.
     * @param search string to search.
     */
    override fun searchMovies(search: String) {
        _loading.postValue(true)
        launch {
            insertMoviesBG(
                moviesApiClient.getMoviesResponse().getMovies(search, COUNTRY, MEDIA),
                search
            )
        }
    }

    /**
     * Insert the movies response from the service into local database.
     * @param moviesResponse movies response from the service.
     * @param search string used to search.
     */
    private suspend fun insertMoviesBG(moviesResponse: MoviesResponse, search: String) {
        withContext(Dispatchers.IO) {
            try {
                _loading.postValue(false)
                val list = moviesResponse.results.clientModelToMovieEntity()
                if (list.isNotEmpty()) {
                    movieDao.deleteAll()
                    movieDao.insertMovies(list)
                    sharedPreferencesManager.lastSearch(
                        SharedPreferencesManagerImpl.LAST_SEARCH,
                        search
                    )
                    _recentSearch.postValue(null)
                } else {
                    _errorBanner.postValue(
                        Pair(
                            true,
                            search
                        )
                    )
                }
            } catch (e: Exception) {
                _loading.postValue(false)
            }
        }
    }

    /**
     * Get the recent search.
     */
    override fun getRecent() {
        val recent = sharedPreferencesManager.lastSearch(SharedPreferencesManagerImpl.LAST_SEARCH)
        _recentSearch.postValue(
            if (!recent.isNullOrEmpty())
                Pair(
                    R.string.recent_search,
                    sharedPreferencesManager.lastSearch(SharedPreferencesManagerImpl.LAST_SEARCH)
                ) else null
        )
    }

    /**
     * Reset error banner.
     */
    override fun resetBanner() {
        _errorBanner.postValue(
            Pair(
                false,
                SharedPreferencesManagerImpl.EMPTY_STRING
            )
        )
    }

    companion object {
        const val COUNTRY = "AU"
        const val MEDIA = "movie"
    }
}
