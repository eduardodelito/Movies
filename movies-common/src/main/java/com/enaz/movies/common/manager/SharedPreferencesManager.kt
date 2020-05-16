package com.enaz.movies.common.manager

import android.content.SharedPreferences

/**
 * Created by eduardo.delito on 5/16/20.
 */
interface SharedPreferencesManager {
    fun lastSearch(key: String?, value: String? = null): String
}

class SharedPreferencesManagerImpl(private val sharedPreferences: SharedPreferences): SharedPreferencesManager {

    override fun lastSearch(key: String?, value: String?): String {
        value?.run {
            with(sharedPreferences.edit()) {
                putString(key, value)
                apply()
            }
        }
        return sharedPreferences.getString(key, EMPTY_STRING) ?: EMPTY_STRING
    }

    companion object {
        const val EMPTY_STRING = ""
        const val LAST_SEARCH = "last_search"
    }
}
