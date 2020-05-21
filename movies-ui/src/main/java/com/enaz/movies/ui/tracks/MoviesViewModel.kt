package com.enaz.movies.ui.tracks

import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import com.enaz.movies.common.viewmodel.BaseViewModel
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
        moviesRepository.searchMovies(sharedPreferencesManager.lastSearch(SharedPreferencesManagerImpl.LAST_SEARCH))
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
}
