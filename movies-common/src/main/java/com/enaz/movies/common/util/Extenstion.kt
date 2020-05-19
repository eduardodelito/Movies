package com.enaz.movies.common.util

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.facebook.drawee.view.SimpleDraweeView

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

fun String.replaceImageTo300() = this.replace("100x100", "300x300")

fun String.replaceImageTo1000() = this.replace("100x100", "1000x1000")