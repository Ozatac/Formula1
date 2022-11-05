package com.tunahanozatac.formula1apps.domain.usecase

import com.tunahanozatac.formula1apps.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}