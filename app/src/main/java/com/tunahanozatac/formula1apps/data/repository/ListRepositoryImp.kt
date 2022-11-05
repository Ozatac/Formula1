package com.tunahanozatac.formula1apps.data.repository

import com.tunahanozatac.formula1apps.core.BaseRepository
import com.tunahanozatac.formula1apps.data.network.ApiServices
import com.tunahanozatac.formula1apps.domain.model.ResponseModel
import com.tunahanozatac.formula1apps.domain.repository.ListRepository
import com.tunahanozatac.formula1apps.util.response.Resource
import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), ListRepository {

    override suspend fun getList(): Resource<ResponseModel> {
        return safeApiRequest {
            apiServices.getList()
        }
    }
}