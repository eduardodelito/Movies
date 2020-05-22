package com.enaz.movies.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enaz.movies.common.util.formatDate
import com.enaz.movies.common.util.formatTime
import com.enaz.movies.common.viewmodel.BaseViewModel
import com.enaz.movies.ui.model.MovieItem

/**
 * DetailsViewModel class
 *
 * Created by eduardo.delito on 5/16/20.
 */
class DetailsViewModel : BaseViewModel() {

    var movieItem: MovieItem? = null
    private val _detailsAvailable = MutableLiveData<Boolean>(false)
    val detailsAvailable: LiveData<Boolean> get() = _detailsAvailable

    fun details(movieItem: MovieItem?) {
        if (movieItem != null)
            _detailsAvailable.postValue(true)
    }

    /**
     * Format date with the method extension formatStringToDate
     * and formatDateToString.
     */
    fun releaseDate() =
        movieItem?.releaseDate?.formatDate(
            DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z,
            DATE_FORMAT_MMMM_dd_yyyy,
            TIME_ZONE
        )

    /**
     * Format time from Long Millis to String hh:mm:ss.
     */
    fun trackTime() = movieItem?.trackTimeMillis?.formatTime()

    companion object {
        const val DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z: String = "yyyy-MM-dd'T'HH:mm:SS'Z'"
        const val DATE_FORMAT_MMMM_dd_yyyy: String = "MMMM dd, yyyy"
        const val TIME_ZONE: String = "UTC"
    }
}
