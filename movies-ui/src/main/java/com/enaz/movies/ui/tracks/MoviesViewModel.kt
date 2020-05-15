package com.enaz.movies.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.viewmodel.BaseViewModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    val errorMessage = moviesRepository.errorMessage

    val loading = moviesRepository.loading

//    fun getMovies() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = moviesRepository.getMovies("")))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    fun getMovies() = moviesRepository.getMovies()

    fun refresh() {

    }

    fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            moviesRepository.searchMovies(query)
        }
        return false
    }
}
