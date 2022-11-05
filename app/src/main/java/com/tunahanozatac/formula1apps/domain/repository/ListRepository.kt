package com.tunahanozatac.formula1apps.domain.repository

import com.tunahanozatac.formula1apps.domain.model.ResponseModel
import com.tunahanozatac.formula1apps.util.response.Resource

interface ListRepository {
    suspend fun getList(): Resource<ResponseModel>
}