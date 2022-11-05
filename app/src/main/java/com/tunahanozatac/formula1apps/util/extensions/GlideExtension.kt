package com.tunahanozatac.formula1apps.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tunahanozatac.formula1apps.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).centerCrop().placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error).transform(CenterCrop(), RoundedCorners(25)).into(this)
}