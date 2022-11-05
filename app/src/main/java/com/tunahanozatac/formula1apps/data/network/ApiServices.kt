package com.tunahanozatac.formula1apps.data.network

import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.domain.model.ResponseModel
import com.tunahanozatac.formula1apps.util.Constants.DETAILS_API
import com.tunahanozatac.formula1apps.util.Constants.LIST_API
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET(LIST_API)
    suspend fun getList(): ResponseModel

    @GET(DETAILS_API)
    suspend fun getDetails(
        @Path("id") id: Int,
    ): Details
}