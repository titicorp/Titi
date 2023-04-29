package com.titicorp.titi.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titicorp.titi.model.SimpleProduct
import com.titicorp.titi.network.TitiApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val api: TitiApi = TitiApi,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadProducts()
        }
    }

    private suspend fun loadProducts() {
        val products = api.getSimpleProducts()
        _uiState.update {
            UiState.Content(
                products = products
            )
        }
    }

    sealed interface UiState {
        object Loading : UiState

        data class Content(
            val products: List<SimpleProduct>
        ) : UiState
    }
}