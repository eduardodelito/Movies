package com.enaz.movies.ui.details

import com.enaz.movies.common.viewmodel.BaseViewModel
import com.enaz.movies.ui.model.MovieItem

class DetailsViewModel : BaseViewModel() {

    var movieItem: MovieItem? = null

    fun updateMovieItem(item: MovieItem?) {
        this.movieItem = item
    }
}