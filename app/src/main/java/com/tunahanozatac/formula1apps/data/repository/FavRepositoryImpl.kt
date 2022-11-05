package com.tunahanozatac.formula1apps.data.repository

import com.tunahanozatac.formula1apps.domain.datasource.LocalDataSource
import com.tunahanozatac.formula1apps.domain.model.Item
import com.tunahanozatac.formula1apps.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : FavoriteRepository {
    override suspend fun addFavorite(item: Item) = localDataSource.addFavorite(item)

    override suspend fun getFavorites(): List<Item> = localDataSource.getFavorites()

    override suspend fun delete(deletedId: Int) = localDataSource.delete(deletedId)
}