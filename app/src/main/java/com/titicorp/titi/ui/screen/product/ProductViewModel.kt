package com.titicorp.titi.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.titicorp.titi.model.Product
import com.titicorp.titi.network.TitiApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val id: String,
    private val api: TitiApi = TitiApi,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadProductDetails()
        }
    }

    private suspend fun loadProductDetails() {
        val product = api.getProductDetails(id)
        _uiState.update {
            UiState.Content(
                product = product
            )
        }
    }

    sealed interface UiState {
        object Loading : UiState

        data class Content(
            val product: Product,
        ) : UiState
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val id: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(id) as T
        }
    }
}