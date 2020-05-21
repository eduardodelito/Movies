package com.enaz.movies.common.util

import android.os.Build

/**
 * Created by eduardo.delito on 5/21/20.
 */
object AndroidVersionUtil {
    fun isLollipopOrAbove(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    fun isNougatOrAbove(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}
