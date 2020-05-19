package com.enaz.movies.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.enaz.movies.ui.details.DetailsFragment
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.ui.tracks.MoviesFragment
import com.enaz.movies.ui.tracks.MoviesFragmentDirections
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), MoviesFragment.OnMoviesFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onMovieSelected(view: View, movieItem: MovieItem?) {
        val detailsFragment: DetailsFragment? =
            supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?
        if (detailsFragment == null) {
            val bundle = bundleOf(DetailsFragment.MOVIE_ITEM to movieItem)
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment()
            view.findNavController().navigate(action.actionId, bundle)
        } else {
            detailsFragment.updateDetails(movieItem)
        }
    }
}
