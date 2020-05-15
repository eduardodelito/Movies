package com.enaz.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.ui.tracks.R
import com.enaz.movies.ui.tracks.databinding.ItemMovieBinding

/**
 * Created by eduardo.delito on 5/16/20.
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var list: List<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemMovieBinding.movieItem = list[position]
        holder.itemMovieBinding.executePendingBindings()
        holder.itemMovieBinding.artworkUrl.setImageURI(list[position].artworkUrl)
    }

    override fun getItemCount() = list.size

    fun updateData(list: List<MovieItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root)
}
