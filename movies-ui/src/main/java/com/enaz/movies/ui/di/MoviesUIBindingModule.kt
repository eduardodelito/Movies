package com.enaz.movies.ui.di

import androidx.lifecycle.ViewModelProvider
import com.enaz.movies.ui.details.DetailsFragment
import com.enaz.movies.ui.details.DetailsViewModel
import com.enaz.movies.ui.tracks.MoviesFragment
import com.enaz.movies.ui.tracks.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Fragment UI binding.
 * Created by eduardo.delito on 5/14/20.
 */
@Module
abstract class MoviesUIBindingModule {

    @ContributesAndroidInjector(modules = [InjectMoviesViewModelModule::class])
    abstract fun bindMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector(modules = [InjectDetailsViewModelModule::class])
    abstract fun bindDetailsFragment(): DetailsFragment

    @Module
    class InjectMoviesViewModelModule {
        @Provides
        internal fun provideMoviesViewModel(
            factory: ViewModelProvider.Factory,
            target: MoviesFragment
        ) = ViewModelProvider(target, factory).get(MoviesViewModel::class.java)
    }

    @Module
    class InjectDetailsViewModelModule {
        @Provides
        internal fun provideDetailsViewModel(
            factory: ViewModelProvider.Factory,
            target: DetailsFragment
        ) = ViewModelProvider(target, factory).get(DetailsViewModel::class.java)
    }
}
