package com.tunahanozatac.formula1apps.domain.usecase

import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.domain.repository.DetailsRepository
import com.tunahanozatac.formula1apps.util.response.Resource
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(id: Int): Resource<Details> {
        return repository.details(id = id)
    }
}