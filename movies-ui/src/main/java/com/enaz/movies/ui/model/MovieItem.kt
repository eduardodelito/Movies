package com.enaz.movies.ui.model

import java.io.Serializable

/**
 * Created by eduardo.delito on 5/16/20.
 */
data class MovieItem (val kind : String?,
                      val trackId : Long?,
                      val artistName : String?,
                      val collectionName : String?,
                      val trackName : String?,
                      val artworkUrl : String?,
                      val collectionPrice : Double?,
                      val trackPrice : Double?,
                      val trackRentalPrice : Double?,
                      val collectionHdPrice : Double?,
                      val trackHdPrice : Double?,
                      val trackHdRentalPrice : Double?,
                      val releaseDate : String?,
                      val collectionExplicitness : String?,
                      val trackExplicitness : String?,
                      val trackCount : Int?,
                      val trackNumber : Int?,
                      val trackTimeMillis : Long?,
                      val country : String?,
                      val currency : String?,
                      val primaryGenreName : String?,
                      val contentAdvisoryRating : String?,
                      val shortDescription : String?,
                      val longDescription : String?) : Serializable