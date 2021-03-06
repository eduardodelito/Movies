package com.enaz.movies.client.model

/**
 * MoviesResponse is the model class used to request from the services.
 *
 * Created by eduardo.delito on 5/15/20.
 */
data class MoviesResponse (val results : List<MoviesResult>, val resultCount : Int)

/**
 * MoviesResult is the model class used to parse movies response from the service.
 */
data class MoviesResult (val wrapperType : String?,
                              val kind : String?,
                              val artistId : Long?,
                              val collectionId : Long?,
                              val trackId : Long?,
                              val artistName : String?,
                              val collectionName : String?,
                              val trackName : String?,
                              val collectionCensoredName : String?,
                              val trackCensoredName : String?,
                              val artistViewUrl : String?,
                              val collectionViewUrl : String?,
                              val trackViewUrl : String?,
                              val previewUrl : String?,
                              val artworkUrl30 : String?,
                              val artworkUrl60 : String?,
                              val artworkUrl100 : String?,
                              val collectionPrice : Double?,
                              val trackPrice : Double?,
                              val trackRentalPrice : Double?,
                              val collectionHdPrice : Double?,
                              val trackHdPrice : Double?,
                              val trackHdRentalPrice : Double?,
                              val releaseDate : String?,
                              val collectionExplicitness : String?,
                              val trackExplicitness : String?,
                              val discCount : Int?,
                              val discNumber : Int?,
                              val trackCount : Int?,
                              val trackNumber : Int?,
                              val trackTimeMillis : Long?,
                              val country : String?,
                              val currency : String?,
                              val primaryGenreName : String?,
                              val isStreamable : Boolean?,
                              val contentAdvisoryRating : String?,
                              val shortDescription : String?,
                              val longDescription : String?)
