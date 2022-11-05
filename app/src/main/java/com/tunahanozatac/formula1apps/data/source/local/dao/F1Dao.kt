package com.tunahanozatac.formula1apps.data.source.local.dao

import androidx.room.Insert
import com.tunahanozatac.formula1apps.domain.model.Item
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface F1Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(item: Item)

    @Query("SELECT * FROM Item")
    suspend fun getFavorites(): List<Item>

    @Query("DELETE FROM Item WHERE id = :deletedId")
    suspend fun delete(deletedId: Int)
}