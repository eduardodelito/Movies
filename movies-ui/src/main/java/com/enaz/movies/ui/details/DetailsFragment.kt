package com.enaz.movies.ui.details

import com.enaz.movies.common.fragment.BaseFragment
import com.enaz.movies.common.util.replaceImageTo1000
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.ui.tracks.BR
import com.enaz.movies.ui.tracks.R
import com.enaz.movies.ui.tracks.databinding.DetailsFragmentBinding
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

/**
 * DetailsFragment to display movie details.
 *
 * Created by eduardo.delito on 5/16/20.
 */
class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    @Inject
    override lateinit var viewModel: DetailsViewModel

    override fun createLayout() = R.layout.details_fragment

    override fun getBindingVariable() = BR.detailsViewModel

    /**
     * Initialize views
     */
    override fun initViews() {
        val movieItem = arguments?.getSerializable(MOVIE_ITEM) as MovieItem?
        updateDetails(movieItem)
    }

    override fun subscribeUi() {
        //Do nothing for now.
    }

    companion object {
        const val MOVIE_ITEM = "movieItem"
        fun newInstance() = DetailsFragment()
    }

    /**
     * Update movie details.
     * @param movieItem data to display details.
     */
    fun updateDetails(movieItem: MovieItem?) {
        viewModel.movieItem = movieItem
        getBinding().executePendingBindings()
        getBinding().invalidateAll()
        movie_image.setImageURI(movieItem?.artworkUrl?.replaceImageTo1000())
    }
}
