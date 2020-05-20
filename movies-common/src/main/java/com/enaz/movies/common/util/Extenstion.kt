package com.enaz.movies.common.util

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

/**
 * Extension function for string to HTML tags
 *
 * @return the spanned string
 */
fun String.supportHTMLTags() : Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this)
    }
}

const val DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z: String = "yyyy-MM-dd'T'HH:mm:SS'Z'"
const val DATE_FORMAT_MMMM_dd_yyyy: String = "MMMM dd, yyyy"

/**
 * Pattern: yyyy-MM-dd'T'HH:mm:SS'Z'
 */
fun String.formatStringToDate(): Date? {
    val sdf = SimpleDateFormat(DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.parse(this)
}

/**
 * Pattern: MMMM dd, yyyy
 */
fun Date.formatDateToString(): String? {
    val sdf = SimpleDateFormat(DATE_FORMAT_MMMM_dd_yyyy, Locale.getDefault())
    return try {
        sdf.format(this)
    } catch (e: ParseException) {
        "Not Available"
    }
}
