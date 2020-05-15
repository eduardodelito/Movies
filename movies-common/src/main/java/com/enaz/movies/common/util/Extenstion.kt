package com.enaz.movies.common.util

import android.view.View
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by eduardo.delito on 5/15/20.
 */

fun AppCompatTextView.setViewVisibility(message: String?) {
    with(this) {
        visibility = message?.let {
            text = message
            View.VISIBLE
        } ?: View.GONE
    }
}
