package com.dev_akash.assignmentlistedapp.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dev_akash.assignmentlistedapp.R
import com.dev_akash.assignmentlistedapp.core.ApplicationKClass


fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.placeholder_logo)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}


fun AppCompatImageView.loadImage(@DrawableRes img: Int?) {
    Glide.with(this.context)
        .load(img)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun View.visibilityGone(){
    this.isVisible = false
}

fun View.visibilityVisible(){
    this.isVisible = true
}


fun getStringResource(@StringRes id:Int): String {
    return ApplicationKClass.INSTANCE.applicationContext.getString(id)
}
