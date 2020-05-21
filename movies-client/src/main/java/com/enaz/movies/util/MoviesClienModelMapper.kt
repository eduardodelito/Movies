package com.enaz.movies.util

import com.enaz.movies.client.model.MoviesResult
import com.enaz.movies.database.entity.MovieEntity

/**
 * Mapper to switch client list model.
 *
 * Created by eduardo.delito on 5/15/20.
 */
fun List<MoviesResult>.clientModelToMovieEntity() : List<MovieEntity> {
    return this.map {
        MovieEntity(
            id = 0,
            kind = it.kind,
            trackId = it.trackId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            trackName = it.trackName,
            artworkUrl = it.artworkUrl100,
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