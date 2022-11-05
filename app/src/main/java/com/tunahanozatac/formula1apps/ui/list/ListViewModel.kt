package com.tunahanozatac.formula1apps.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.formula1apps.domain.model.Item
import com.tunahanozatac.formula1apps.domain.model.ResponseModel
import com.tunahanozatac.formula1apps.domain.usecase.DeleteUseCase
import com.tunahanozatac.formula1apps.domain.usecase.FavUseCase
import com.tunahanozatac.formula1apps.domain.usecase.GetFavUseCase
import com.tunahanozatac.formula1apps.domain.usecase.GetListUseCase
import com.tunahanozatac.formula1apps.util.extensions.launchOnIO
import com.tunahanozatac.formula1apps.util.response.Resource
import com.tunahanozatac.formula1apps.util.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetListUseCase,
    private val favUseCase: FavUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val getFavUseCase: GetFavUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<ResponseModel?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))
    val uiState: StateFlow<Resource<ResponseModel?>> get() = _uiState

    fun getList(): StateFlow<Resource<ResponseModel?>> {
        viewModelScope.launchOnIO {
            when (val response = useCase.invoke()) {
                is Resource.Success -> {
                    getAllFav(response.data, response.state)
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

    private fun getAllFav(data: ResponseModel?, state: UIStatus?) {
        viewModelScope.launch {
            val fav = getFavUseCase()
            data?.items?.forEach { list ->
                list.isFavorite = fav.any { list.id == it.id }
            }
            _uiState.emit(Resource.Success(data, state))
        }
    }

    fun addToFavorite(item: Item) {
        viewModelScope.launch {
            favUseCase(item)
        }
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteUseCase(id)
        }
    }
}