package com.enaz.movies.ui.util

import com.enaz.movies.client.model.MoviesResult
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.ui.model.MovieItem
import com.enaz.movies.util.clientModelToMovieEntity

/**
 * Created by eduardo.delito on 5/16/20.
 */

fun List<MovieEntity>.entityModelToMovieItem() : List<MovieItem> {
    return this.map {
        MovieItem(
            kind = it.kind,
            trackId = it.trackId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            trackName = it.trackName,
            artworkUrl = it.artworkUrl,
            collectionPrice = it.collectionPrice,
            trackPrice = it.trackPrice,
            trackRentalPrice = it.trackRentalPrice,
            collectionHdPrice = it.collectionHdPrice,
            trackHdPrice = it.trackHdPrice,
            trackHdRentalPrice = it.trackHdRentalPrice,
            releaseDate = it.releaseDate,
            collectionExplicitness = it.collectionExplicitness,
            trackExplicitness = it.trackExplicitness,
            trackCount = it.trackCount,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            contentAdvisoryRating = it.contentAdvisoryRating,
            shortDescription = it.shortDescription,
            longDescription = it.longDescription
        )
    }
}
