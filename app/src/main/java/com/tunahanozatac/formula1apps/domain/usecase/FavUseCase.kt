package com.tunahanozatac.formula1apps.domain.usecase

import com.tunahanozatac.formula1apps.domain.model.Item
import com.tunahanozatac.formula1apps.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(item: Item) = repository.addFavorite(item)
}