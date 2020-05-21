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

/**
 * Main Activity Class
 *
 * Created by eduardo.delito on 5/14/20.
 */
class MainActivity : DaggerAppCompatActivity(), MoviesFragment.OnMoviesFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * onClick method to display details for mobile/tablet.
     * @param view used to navigate fragment.
     * @param movieItem data to display details.
     */
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

    /**
     * Display first index as default details.
     * @param movieItem data to display details.
     */
    override fun loadFirstIndex(movieItem: MovieItem?) {
        (supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?)?.updateDetails(movieItem)
    }
}
