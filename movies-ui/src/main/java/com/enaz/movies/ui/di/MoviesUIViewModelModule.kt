package com.enaz.movies.ui.di

import androidx.lifecycle.ViewModel
import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.viewmodel.ViewModelKey
import com.enaz.movies.ui.details.DetailsViewModel
import com.enaz.movies.ui.tracks.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Module class for provided view models.
 *
 * Created by eduardo.delito on 5/14/20.
 */
@Module
class MoviesUIViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun provideMoviesViewModel(
        moviesRepository: MoviesRepository,
        sharedPreferencesManager: SharedPreferencesManager
    ): ViewModel =
        MoviesViewModel(moviesRepository, sharedPreferencesManager)

    @Provides
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun provideDetailsViewModel(): ViewModel = DetailsViewModel()
}
