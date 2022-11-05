package com.tunahanozatac.formula1apps.domain.model

data class Details(
    val age: Int, val id: Int, val image: String, val team: String, var isFavorite: Boolean = false
)