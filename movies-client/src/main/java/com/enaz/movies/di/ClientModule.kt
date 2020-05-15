package com.enaz.movies.di

import com.enaz.movies.client.MoviesApiClient
import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.client.repository.MoviesRepositoryImpl
import com.enaz.movies.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by eduardo.delito on 5/15/20.
 */
@Module
class ClientModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideMoviesApiClient(okHttpClient: OkHttpClient.Builder) = MoviesApiClient(okHttpClient)

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesApiClient: MoviesApiClient, movieDao: MovieDao): MoviesRepository =
        MoviesRepositoryImpl(moviesApiClient, movieDao)
}
