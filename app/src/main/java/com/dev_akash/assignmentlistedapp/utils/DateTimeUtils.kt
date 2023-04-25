package com.dev_akash.assignmentlistedapp.utils

import android.content.res.Resources
import android.icu.text.DateFormatSymbols
import com.dev_akash.assignmentlistedapp.R
import org.threeten.bp.LocalDate
import java.util.*


object DateTimeUtils {

    fun getMonthValueFromDate(date: String): Float {
        return LocalDate.parse(date).monthValue.toFloat()
    }

    fun getGreetingText(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> Resources.getSystem().getString(R.string.good_morning)
            in 12..15 -> Resources.getSystem().getString(R.string.afternoon)
            in 16..20 -> Resources.getSystem().getString(R.string.good_evening)
            in 21..23 -> Resources.getSystem().getString(R.string.good_night)
            else -> Resources.getSystem().getString(R.string.hi)
        }
    }

    fun getFormattedDate(date: Date?): String {
        return try {
            val cal = Calendar.getInstance()
            date?.let { cal.time = date }
            return "${cal.get(Calendar.DAY_OF_MONTH)}" +
                    " ${getShortMonthName(cal.get(Calendar.MONTH))} " +
                    "${cal.get(Calendar.YEAR)}"

        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun getShortMonthName(month: Int): String {
        return DateFormatSymbols().shortMonths[month]
    }
}