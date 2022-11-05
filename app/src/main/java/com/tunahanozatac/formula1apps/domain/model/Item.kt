package com.tunahanozatac.formula1apps.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Item")
data class Item(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "point") val point: Int,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false
) : Parcelable