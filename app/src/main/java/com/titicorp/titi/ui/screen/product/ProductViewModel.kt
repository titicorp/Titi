package com.titicorp.titi.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.titicorp.titi.model.Product
import com.titicorp.titi.model.SimpleProduct
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

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState()
    )
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadProductDetails()
            loadMoreItemsFromUser()
            loadSimilarProducts()
        }
    }

    private suspend fun loadProductDetails() = viewModelScope.launch {
        val product = api.getProductDetails(id)
        _uiState.update {
            it.copy(
                product = product
            )
        }
    }

    private suspend fun loadMoreItemsFromUser() = viewModelScope.launch {
        val items = api.getMoreItemsFromUser(id)
        _uiState.update {
            it.copy(
                moreItemsFromUser = items
            )
        }
    }

    private suspend fun loadSimilarProducts() = viewModelScope.launch {
        val items = api.getSimilarItems(id)
        _uiState.update {
            it.copy(
                similarItems = items
            )
        }
    }

    data class UiState(
        val product: Product? = null,
        val moreItemsFromUser: List<SimpleProduct>? = null,
        val similarItems: List<SimpleProduct>? = null,
    )

    @Suppress("UNCHECKED_CAST")
    class Factory(private val id: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(id) as T
        }
    }
}