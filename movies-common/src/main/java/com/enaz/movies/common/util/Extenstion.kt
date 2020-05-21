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

/**
 * Enable/Disable AppCompatTextView.
 * @param message set message value if available.
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

const val NOT_AVAILABLE: String = "Not Available"

/**
 * Format Date
 * @param fromFormat original format of date.
 * @param toFormat to the new format of date.
 * @param timeZone target timezone.
 */
fun String.formatDate(fromFormat: String, toFormat: String, timeZone: String?): String? {
    return try {
        val simpleDateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone(timeZone)
        simpleDateFormat.parse(this)?.let {
            SimpleDateFormat(toFormat, Locale.getDefault()).format(it)
        } ?: NOT_AVAILABLE
    } catch (e: ParseException) {
        NOT_AVAILABLE
    }
}

/**
 * Date to string format.
 * @param format pattern
 */
fun Date.formatDateToString(format: String?): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}

const val TIME_FORMAT: String = "%02d:%02d:%02d"

/**
 * Format time
 * Long millis to string hh:mm:ss
 */
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
