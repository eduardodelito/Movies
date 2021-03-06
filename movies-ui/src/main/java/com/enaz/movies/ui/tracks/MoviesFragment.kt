package com.enaz.movies.ui.tracks

import android.content.Context
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.enaz.movies.common.dialog.Banner
import com.enaz.movies.common.dialog.ErrorBannerFragment
import com.enaz.movies.common.fragment.BaseFragment
import com.enaz.movies.common.util.setViewVisibility
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.ui.adapter.MoviesAdapter
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.ui.tracks.databinding.MoviesFragmentBinding
import com.enaz.movies.ui.util.entityModelToMovieItem
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

/**
 * Movie list fragment.
 *
 * Created by eduardo.delito on 5/16/20.
 */
class MoviesFragment : BaseFragment<MoviesFragmentBinding, MoviesViewModel>(),
    SearchView.OnQueryTextListener {

    @Inject
    override lateinit var viewModel: MoviesViewModel

    private lateinit var moviesAdapter: MoviesAdapter

    private var listener: OnMoviesFragmentListener? = null

    private var errorBannerFragment: ErrorBannerFragment? = null
    private val TAG: String = MoviesFragment::class.java.simpleName

    override fun createLayout() = R.layout.movies_fragment

    override fun getBindingVariable() = BR.movieListViewModel

    /**
     * Initialize views
     */
    override fun initViews() {
        moviesAdapter = MoviesAdapter(object : MoviesAdapter.OnMoviesAdapterListener {
            /**
             * onClick method to display details for mobile/tablet.
             * @param view used to navigate fragment.
             * @param movieItem data to display details.
             */
            override fun onMovieSelected(view: View, movieItem: MovieItem) {
                listener?.onMovieSelected(view, movieItem)
            }
        })

        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = moviesAdapter
        }

        search_view.setOnQueryTextListener(this)

        swipe_to_refresh_view.setOnRefreshListener {
            viewModel.refresh()
        }
        previously_visited.setViewVisibility(
            if (!viewModel.previouslyVisited().isNullOrEmpty()) getString(
                R.string.previously_visited,
                viewModel.previouslyVisited()
            ) else null
        )
    }

    /**
     * Initialize view model with LiveData functions.
     */
    override fun subscribeUi() {
        with(viewModel) {
            recentSearch.observe(viewLifecycleOwner, Observer { result ->
                search_text_result.setViewVisibility(result?.let {
                    getString(it.first, it.second)
                })
            })

            errorBanner.observe(viewLifecycleOwner, Observer { result ->
                result?.let {
                    if (it.first) showErrorBanner(
                        getString(
                            R.string.no_search_found,
                            result.second
                        )
                    )
                }
            })

            loading.observe(viewLifecycleOwner, Observer { isLoading ->
                swipe_to_refresh_view.isRefreshing = isLoading
            })

            getMovies().observe(viewLifecycleOwner, Observer<List<MovieEntity>> { list ->
                if (list.isNotEmpty()) {
                    val movies = list.entityModelToMovieItem()
                    no_available_data_view.visibility = View.GONE
                    moviesAdapter.updateData(movies)
                    listener?.loadFirstIndex(movies[0])
                }
            })

            getRecent()
        }
    }

    override fun onQueryTextSubmit(query: String?) = viewModel.onQueryTextSubmit(query)

    override fun onQueryTextChange(newText: String?) = false

    /**
     *  Show error banner listener
     *  @param message
     */
    private fun showErrorBanner(message: String?) {
        val errorBannerListener: ErrorBannerFragment.ErrorBannerListener =
            object : ErrorBannerFragment.ErrorBannerListener {
                override fun onErrorBannerRetry(tag: String?) {
                    //TODO: Do nothing
                }

                override fun onErrorBannerDismiss(tag: String?) {
                    viewModel.resetBanner()
                }
            }
        val bannerBuilder: Banner.Builder = Banner.from(message, activity)
            .cancelable()
            .transactional()
            .modal()
            .addListener(errorBannerListener)

        errorBannerFragment = bannerBuilder.build()
        errorBannerFragment?.show(
            childFragmentManager,
            TAG
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMoviesFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Movies fragment listener.
     */
    interface OnMoviesFragmentListener {
        /**
         * onClick method to display details for mobile/tablet.
         * @param view used to navigate fragment.
         * @param movieItem data to display details.
         */
        fun onMovieSelected(view: View, movieItem: MovieItem?)

        /**
         * Display first index as default details.
         * @param movieItem data to display details.
         */
        fun loadFirstIndex(movieItem: MovieItem?)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
