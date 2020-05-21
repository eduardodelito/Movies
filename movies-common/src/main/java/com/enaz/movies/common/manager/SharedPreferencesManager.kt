package com.enaz.movies.common.manager

import android.content.SharedPreferences

/**
 * Manager for shared preferences,
 * it is for internal storage purposes.
 *
 * Created by eduardo.delito on 5/16/20.
 */
interface SharedPreferencesManager {
    /**
     * Method for the last search key.
     * Saving/retrieving string key
     * @param key
     * @param value
     */
    fun lastSearch(key: String?, value: String? = null): String
}

class SharedPreferencesManagerImpl(private val sharedPreferences: SharedPreferences): SharedPreferencesManager {

    /**
     * Method for the last search key.
     * Saving/retrieving string key
     * @param key
     * @param value
     */
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
