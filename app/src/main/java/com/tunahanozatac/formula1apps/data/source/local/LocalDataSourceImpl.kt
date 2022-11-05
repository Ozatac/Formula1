package com.tunahanozatac.formula1apps.data.source.local

import com.tunahanozatac.formula1apps.data.source.local.dao.F1Dao
import com.tunahanozatac.formula1apps.domain.datasource.LocalDataSource
import com.tunahanozatac.formula1apps.domain.model.Item
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val favoriteDAO: F1Dao, private val ioDispatcher: CoroutineContext
) : LocalDataSource {
    override suspend fun addFavorite(item: Item) = withContext(ioDispatcher) {
        favoriteDAO.addFavorite(item)
    }

    override suspend fun getFavorites(): List<Item> = withContext(ioDispatcher) {
        favoriteDAO.getFavorites()
    }

    override suspend fun delete(deletedId: Int) = withContext(ioDispatcher) {
        favoriteDAO.delete(deletedId)
    }
}