package com.enaz.movies.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enaz.movies.database.entity.MovieEntity
import com.enaz.movies.database.util.Resource

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * from MovieEntity ORDER BY id ASC")
    fun getMovies() : LiveData<List<MovieEntity>>

    @Query("DELETE FROM MovieEntity")
    fun deleteAll()
}
