package com.enaz.movies.ui.details

import com.enaz.movies.common.util.formatDateToString
import com.enaz.movies.common.util.formatStringToDate
import com.enaz.movies.common.viewmodel.BaseViewModel
import com.enaz.movies.ui.model.MovieItem

class DetailsViewModel : BaseViewModel() {

    var movieItem: MovieItem? = null

    fun updateMovieItem(item: MovieItem?) {
        this.movieItem = item
    }

    fun price() =
        PRICE + SPACE_SEPARATOR + movieItem?.trackPrice.toString() + SPACE_SEPARATOR + movieItem?.currency

    fun releaseDate() =
        movieItem?.releaseDate?.formatStringToDate()?.formatDateToString()

    companion object {
        const val PRICE = "Price"
        const val SPACE_SEPARATOR = " "
    }
}
