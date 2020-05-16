package com.enaz.movies.ui.di.module

import com.enaz.movies.common.di.CommonModule
import com.enaz.movies.database.di.DatabaseModule
import com.enaz.movies.di.ClientModule
import dagger.Module

/**
 * Created by eduardo.delito on 5/14/20.
 */
@Module(
    includes = [
        DatabaseModule::class,
        ClientModule::class,
        CommonModule::class
    ]
)
class MoviesModule
