package com.dev_akash.assignmentlistedapp.utils

import android.icu.text.DateFormatSymbols
import org.threeten.bp.LocalDate
import java.util.*


object DateTimeUtils {

    fun getMonthValueFromDate(date: String): Float {
        return LocalDate.parse(date).monthValue.toFloat()
    }

    fun getGreetingText(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> "Hi!"
        }
    }

    fun getFormattedDate(date: Date?): String {
        return try {
            val cal = Calendar.getInstance()
            date?.let { cal.time = date }
            return "${cal.get(Calendar.DAY_OF_MONTH)}" +
                    " ${DateFormatSymbols().shortMonths[cal.get(Calendar.MONTH)]} " +
                    "${cal.get(Calendar.YEAR)}"

        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}