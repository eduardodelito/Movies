package com.enaz.movies.ui.di.component

import android.app.Application
import com.enaz.movies.common.di.CommonModule
import com.enaz.movies.database.di.DatabaseModule
import com.enaz.movies.di.ClientModule
import com.enaz.movies.ui.MoviesApplication
import com.enaz.movies.ui.di.module.ActivityBindingModule
import com.enaz.movies.ui.di.module.MoviesModule
import com.enaz.movies.ui.di.module.ViewModelBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBindingModule::class,
    ViewModelBindingModule::class,
    MoviesModule::class
])
interface MoviesComponent : AndroidInjector<MoviesApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun database(databaseModule: DatabaseModule): Builder
        fun client(clientModule: ClientModule): Builder
        fun common(commonModule: CommonModule): Builder
        fun build(): MoviesComponent
    }
}
