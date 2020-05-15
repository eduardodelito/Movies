package com.enaz.movies.ui

import com.enaz.movies.database.di.DatabaseModule
import com.enaz.movies.di.ClientModule
import com.enaz.movies.ui.di.component.DaggerMoviesComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by eduardo.delito on 5/14/20.
 */
class MoviesApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMoviesComponent
            .builder()
            .application(this)
            .database(DatabaseModule(this))
            .client(ClientModule())
            .build()
    }
}
