package com.enaz.movies.common.util

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

const val IMAGE_SIZE_100: String = "100x100"
const val IMAGE_SIZE_300: String = "300x300"
const val IMAGE_SIZE_1000: String = "1000x1000"

fun String.replaceImageTo300() = this.replace(IMAGE_SIZE_100, IMAGE_SIZE_300)

fun String.replaceImageTo1000() = this.replace(IMAGE_SIZE_100, IMAGE_SIZE_1000)

const val DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z: String = "yyyy-MM-dd'T'HH:mm:SS'Z'"
const val DATE_FORMAT_MMMM_dd_yyyy: String = "MMMM dd, yyyy"
const val TIME_ZONE: String = "UTC"

/**
 * Pattern: yyyy-MM-dd'T'HH:mm:SS'Z'
 */
fun String.formatStringToDate(): Date? {
    val sdf = SimpleDateFormat(DATE_FORMAT_yyyy_MM_DD_T_HH_mm_SS_Z, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone(TIME_ZONE)
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

const val TIME_FORMAT: String = "%02d:%02d:%02d"

fun Long.formatTime(): String {
    return String.format(
        TIME_FORMAT, TimeUnit.MILLISECONDS.toHours(this),
        TimeUnit.MILLISECONDS.toMinutes(this).minus(
            TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    this
                )
            )
        ),
        TimeUnit.MILLISECONDS.toSeconds(this).minus(
            TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    this
                )
            )
        )
    )
}
