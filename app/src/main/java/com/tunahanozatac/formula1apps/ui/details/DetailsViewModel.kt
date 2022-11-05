package com.tunahanozatac.formula1apps.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.domain.usecase.DetailsUseCase
import com.tunahanozatac.formula1apps.util.extensions.launchOnIO
import com.tunahanozatac.formula1apps.util.response.Resource
import com.tunahanozatac.formula1apps.util.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: DetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<Details?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))
    val uiState: StateFlow<Resource<Details?>> get() = _uiState

    fun getDetails(id: Int): StateFlow<Resource<Details?>> {
        viewModelScope.launchOnIO {
            when (val response = useCase.invoke(id = id)) {
                is Resource.Success -> {
                    _uiState.emit(Resource.Success(response.data, response.state))
                }
                is Resource.Error -> {
                    _uiState.emit(
                        Resource.Error(
                            "R.string.CheckYourInternetConnection", response.state
                        )
                    )
                }
                is Resource.Loading -> {
                    _uiState.emit(Resource.Loading(UIStatus.LOADING))
                }
            }
        }
        return _uiState
    }
}