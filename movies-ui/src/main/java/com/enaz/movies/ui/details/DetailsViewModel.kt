package com.enaz.movies.ui.details

import com.enaz.movies.common.util.formatDateToString
import com.enaz.movies.common.util.formatStringToDate
import com.enaz.movies.common.util.formatTime
import com.enaz.movies.common.viewmodel.BaseViewModel
import com.enaz.movies.ui.model.MovieItem

class DetailsViewModel : BaseViewModel() {

    var movieItem: MovieItem? = null

    fun releaseDate() =
        movieItem?.releaseDate?.formatStringToDate()?.formatDateToString()

    fun trackTime() = movieItem?.trackTimeMillis?.formatTime()
}
