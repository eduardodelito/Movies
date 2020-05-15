package com.enaz.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enaz.movies.database.dao.MovieDao
import com.enaz.movies.database.entity.MovieEntity

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "MovieDatabase"
    }
}
