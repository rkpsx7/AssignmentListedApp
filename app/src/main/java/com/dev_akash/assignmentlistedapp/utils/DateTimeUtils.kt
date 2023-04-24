package com.dev_akash.assignmentlistedapp.utils

import org.threeten.bp.LocalDate


object DateTimeUtils {

    fun getMonthValueFromDate(date: String): Float {
        return LocalDate.parse(date).monthValue.toFloat()
    }
}