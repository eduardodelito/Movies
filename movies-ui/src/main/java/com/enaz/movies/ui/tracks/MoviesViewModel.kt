package com.enaz.movies.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enaz.movies.client.R
import com.enaz.movies.client.model.MoviesResponse
import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import com.enaz.movies.common.util.formatDateToString
import com.enaz.movies.common.viewmodel.BaseViewModel
import com.enaz.movies.util.clientModelToMovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * MoviesViewModel class with constructor
 * @param moviesRepository for data functions.
 * @param sharedPreferencesManager for local storage.
 *
 * Created by eduardo.delito on 5/16/20.
 */
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) :
    BaseViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val _recentSearch = MutableLiveData<Pair<Int, String>?>()
    val recentSearch: LiveData<Pair<Int, String>?> get() = _recentSearch

    private val _errorBanner = MutableLiveData<Pair<Boolean, String>?>()
    val errorBanner: LiveData<Pair<Boolean, String>?> get() = _errorBanner

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    /**
     * Get the recent search.
     */
    fun getRecent() {
        val recent = sharedPreferencesManager.savePrefs(SharedPreferencesManagerImpl.LAST_SEARCH)
        _recentSearch.postValue(
            if (!recent.isNullOrEmpty())
                Pair(
                    R.string.recent_search,
                    sharedPreferencesManager.savePrefs(SharedPreferencesManagerImpl.LAST_SEARCH)
                ) else null
        )
    }

    /**
     * Get movie list from local data base.
     */
    fun getMovies() = moviesRepository.getMovies()

    /**
     * Refresh movie list from the lat search.
     */
    fun refresh() {
        searchMovies(
            sharedPreferencesManager.savePrefs(
                SharedPreferencesManagerImpl.LAST_SEARCH
            )
        )
    }

    /**
     * Search movies based on the query string value.
     * @param query string to search.
     */
    fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchMovies(query)
        }
        return false
    }

    /**
     * Search movies based on the query string value.
     * @param query string to search
     */
    private fun searchMovies(query: String?) {
        _loading.postValue(true)
        launch {
            insertMoviesBG(moviesRepository.getMoviesResponse().getMovies(query, COUNTRY, MEDIA), query)
        }
    }

    /**
     * Insert the movies response from the service into local database.
     * @param moviesResponse movies response from the service.
     * @param search string used to search.
     */
    private suspend fun insertMoviesBG(moviesResponse: MoviesResponse, search: String?) {
        withContext(Dispatchers.IO) {
            try {
                _loading.postValue(false)
                val list = moviesResponse.results.clientModelToMovieEntity()
                if (list.isNotEmpty()) {
                    moviesRepository.deleteMovies()
                    moviesRepository.insertMovies(list)
                    sharedPreferencesManager.savePrefs(
                        SharedPreferencesManagerImpl.LAST_SEARCH,
                        search
                    )
                    _recentSearch.postValue(null)
                } else {
                    search?.let { _errorBanner.postValue(Pair(true, it)) }
                }
            } catch (e: Exception) {
                _loading.postValue(false)
            }
        }
    }

    /**
     * Reset error banner.
     */
    fun resetBanner() {
        _errorBanner.postValue(
            Pair(
                false,
                SharedPreferencesManagerImpl.EMPTY_STRING
            )
        )
    }

    /**
     * Get previously visited date in string.
     */
    fun previouslyVisited() =
        sharedPreferencesManager.savePrefs(SharedPreferencesManagerImpl.PREVIOUSLY_VISITED)

    /**
     * Save previously visited date inside the onDestroy method.
     */
    override fun onDestroy() {
        super.onDestroy()
        sharedPreferencesManager.savePrefs(
            SharedPreferencesManagerImpl.PREVIOUSLY_VISITED,
            Calendar.getInstance().time.formatDateToString(DATE_FORMAT_MMMM_dd_yyyy)
        )
    }

    companion object {
        const val COUNTRY = "AU"
        const val MEDIA = "movie"
        const val DATE_FORMAT_MMMM_dd_yyyy: String = "EEEE MMMM dd, yyyy"
    }
}
