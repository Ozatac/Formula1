package com.tunahanozatac.formula1apps.domain.datasource

import com.tunahanozatac.formula1apps.domain.model.Item

interface LocalDataSource {

    suspend fun addFavorite(item: Item)

    suspend fun getFavorites(): List<Item>

    suspend fun delete(deletedId: Int)
}