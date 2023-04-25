package com.dev_akash.assignmentlistedapp.model

import androidx.annotation.DrawableRes

data class Stats(
    @DrawableRes val img: Int,
    val data: String,
    val title: String
)