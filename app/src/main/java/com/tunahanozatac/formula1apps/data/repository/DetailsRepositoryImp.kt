package com.tunahanozatac.formula1apps.data.repository

import com.tunahanozatac.formula1apps.core.BaseRepository
import com.tunahanozatac.formula1apps.data.network.ApiServices
import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.domain.repository.DetailsRepository
import com.tunahanozatac.formula1apps.util.response.Resource
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), DetailsRepository {
    override suspend fun details(id: Int): Resource<Details> {
        return safeApiRequest {
            apiServices.getDetails(id = id)
        }
    }
}