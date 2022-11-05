package com.tunahanozatac.formula1apps.domain.usecase

import com.tunahanozatac.formula1apps.domain.model.ResponseModel
import com.tunahanozatac.formula1apps.domain.repository.ListRepository
import com.tunahanozatac.formula1apps.util.response.Resource
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: ListRepository
) {
    suspend operator fun invoke(): Resource<ResponseModel> {
        return repository.getList()
    }
}