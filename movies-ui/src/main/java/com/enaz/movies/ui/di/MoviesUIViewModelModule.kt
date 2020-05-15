package com.enaz.movies.ui.di

import androidx.lifecycle.ViewModel
import com.enaz.movies.client.repository.MoviesRepository
import com.enaz.movies.common.viewmodel.ViewModelKey
import com.enaz.movies.ui.tracks.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Module
class MoviesUIViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun provideAddViewModel(moviesRepository: MoviesRepository): ViewModel =
        MoviesViewModel(moviesRepository)
}