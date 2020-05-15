package com.enaz.movies.database.di

import android.app.Application
import androidx.room.Room
import com.enaz.movies.database.MoviesDatabase
import com.enaz.movies.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Module
class DatabaseModule(private val mApplication: Application) {
    private var moviesDatabase: MoviesDatabase

    init {
        synchronized(this) {
            val instance = Room.databaseBuilder(
                mApplication,
                MoviesDatabase::class.java,
                MoviesDatabase.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
            moviesDatabase = instance
            instance
        }
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): MoviesDatabase {
        return moviesDatabase
    }

    @Singleton
    @Provides
    fun providesMovieDao(moviesDatabase: MoviesDatabase): MovieDao {
        return moviesDatabase.moviesDao()
    }
}
