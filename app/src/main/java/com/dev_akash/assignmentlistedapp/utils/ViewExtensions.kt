package com.dev_akash.assignmentlistedapp.utils

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dev_akash.assignmentlistedapp.R
import java.io.File


fun AppCompatImageView.loadImage(@DrawableRes img: Int?) {
    Glide.with(this.context)
        .load(img)
        .placeholder(R.drawable.placeholder_logo)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.placeholder_logo)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

