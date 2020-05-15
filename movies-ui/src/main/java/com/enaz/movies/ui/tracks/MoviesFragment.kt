package com.enaz.movies.ui.tracks

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.enaz.movies.common.fragment.BaseFragment
import com.enaz.movies.common.util.setViewVisibility
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.ui.adapter.MoviesAdapter
import com.enaz.movies.ui.tracks.databinding.MoviesFragmentBinding
import com.enaz.movies.ui.util.entityModelToMovieItem
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

class MoviesFragment : BaseFragment<MoviesFragmentBinding, MoviesViewModel>(),
    SearchView.OnQueryTextListener {

    @Inject
    override lateinit var viewModel: MoviesViewModel

    private lateinit var moviesAdapter: MoviesAdapter

    override fun createLayout() = R.layout.movies_fragment

    override fun getBindingVariable() = BR.movieListViewModel

    override fun initData() {

    }

    override fun initViews() {
        moviesAdapter = MoviesAdapter()

        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
            adapter = moviesAdapter
        }

        search_view.setOnQueryTextListener(this)

        swipe_to_refresh_view.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun subscribeUi() {
        with(viewModel) {

            errorMessage.observe(viewLifecycleOwner, Observer { messageId ->
                error_view.setViewVisibility(messageId?.let {
                    getString(messageId)
                })
            })

            loading.observe(viewLifecycleOwner, Observer { isLoading ->
                swipe_to_refresh_view.isRefreshing = isLoading
            })

            getMovies().observe(viewLifecycleOwner, Observer<List<MovieEntity>> { list ->
                if (list.isNotEmpty()) {
                    moviesAdapter.updateData(list.entityModelToMovieItem())
                }
            })
        }
    }

    override fun onQueryTextSubmit(query: String?) = viewModel.onQueryTextSubmit(query)

    override fun onQueryTextChange(newText: String?) = false

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
