package com.tunahanozatac.formula1apps.domain.repository

import com.tunahanozatac.formula1apps.domain.model.Item

interface FavoriteRepository {

    suspend fun addFavorite(item: Item)

    suspend fun getFavorites(): List<Item>

    suspend fun delete(deletedId: Int)
}