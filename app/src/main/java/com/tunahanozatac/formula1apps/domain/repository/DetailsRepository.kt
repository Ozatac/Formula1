package com.tunahanozatac.formula1apps.domain.repository

import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.util.response.Resource

interface DetailsRepository {
    suspend fun details(id: Int): Resource<Details>
}