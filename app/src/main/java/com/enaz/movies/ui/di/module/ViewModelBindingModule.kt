package com.enaz.movies.ui.di.module

import androidx.lifecycle.ViewModelProvider
import com.enaz.movies.common.viewmodel.ViewModelFactory
import com.enaz.movies.ui.di.MoviesUIViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Class for view model module.
 *
 * Created by eduardo.delito on 5/14/20.
 */
@Module(
    includes = [
        MoviesUIViewModelModule::class
    ]
)
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
