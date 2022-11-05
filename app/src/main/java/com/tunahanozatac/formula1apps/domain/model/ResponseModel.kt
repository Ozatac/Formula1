package com.tunahanozatac.formula1apps.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseModel(
    val items: List<Item>
): Parcelable