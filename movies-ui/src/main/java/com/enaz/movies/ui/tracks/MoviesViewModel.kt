package com.enaz.movies.ui.tracks

import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import com.enaz.movies.common.util.formatDateToString
import com.enaz.movies.common.viewmodel.BaseViewModel
import java.util.*
import javax.inject.Inject

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
    BaseViewModel() {

    val recentSearch = moviesRepository.recentSearch

    val errorBanner = moviesRepository.errorBanner

    val loading = moviesRepository.loading

    /**
     * Display recent search.
     */
    init {
        moviesRepository.getRecent()
    }

    /**
     * Get movie list from local data base.
     */
    fun getMovies() = moviesRepository.getMovies()

    /**
     * Refresh movie list from the lat search.
     */
    fun refresh() {
        moviesRepository.searchMovies(
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
            moviesRepository.searchMovies(query)
        }
        return false
    }

    /**
     * Reset error banner.
     */
    fun resetBanner() = moviesRepository.resetBanner()

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
        const val DATE_FORMAT_MMMM_dd_yyyy: String = "EEEE MMMM dd, yyyy"
    }
}
