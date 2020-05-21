package com.enaz.movies.common.di

import android.content.Context
import android.content.SharedPreferences
import com.enaz.movies.common.manager.SharedPreferencesManager
import com.enaz.movies.common.manager.SharedPreferencesManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Common module with the provided methods.
 *
 * Created by eduardo.delito on 5/16/20.
 */
@Module
class CommonModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences): SharedPreferencesManager =
        SharedPreferencesManagerImpl(sharedPreferences)

    companion object {
        private const val STORE_NAME = "movies_prefs"
    }
}
