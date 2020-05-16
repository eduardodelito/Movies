package com.enaz.movies.ui.tracks

import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import com.enaz.movies.common.viewmodel.BaseViewModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) :
    BaseViewModel() {

    val recentSearch = moviesRepository.recentSearch

    val errorBanner = moviesRepository.errorBanner

    val loading = moviesRepository.loading

    init {
        moviesRepository.getRecent()
    }

    fun getMovies() = moviesRepository.getMovies()

    fun refresh() {
        moviesRepository.searchMovies(sharedPreferencesManager.lastSearch(SharedPreferencesManagerImpl.LAST_SEARCH))
    }

    fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            moviesRepository.searchMovies(query)
        }
        return false
    }

    fun resetBanner() = moviesRepository.resetBanner()
}
