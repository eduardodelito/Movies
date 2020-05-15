package com.enaz.movies.ui.di.module

import com.enaz.movies.ui.MainActivity
import com.enaz.movies.ui.di.MoviesUIBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [MoviesUIBindingModule::class])

    abstract fun bindMainActivity(): MainActivity
}