package com.enaz.movies.ui.details

import com.enaz.movies.common.fragment.BaseFragment
import com.enaz.movies.common.util.replaceImageTo1000
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.ui.tracks.BR
import com.enaz.movies.ui.tracks.R
import com.enaz.movies.ui.tracks.databinding.DetailsFragmentBinding
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    @Inject
    override lateinit var viewModel: DetailsViewModel

    override fun createLayout() = R.layout.details_fragment

    override fun getBindingVariable() = BR.detailsViewModel

    override fun initViews() {
        val movieItem = arguments?.getSerializable(MOVIE_ITEM) as MovieItem?
        updateDetails(movieItem)
    }

    override fun subscribeUi() {

    }

    companion object {
        const val MOVIE_ITEM = "movieItem"
        fun newInstance() = DetailsFragment()
    }

    fun updateDetails(movieItem: MovieItem?) {
        viewModel.updateMovieItem(movieItem)
        getBinding().executePendingBindings()
        getBinding().invalidateAll()
        movie_image.setImageURI(movieItem?.artworkUrl?.replaceImageTo1000())
    }
}
