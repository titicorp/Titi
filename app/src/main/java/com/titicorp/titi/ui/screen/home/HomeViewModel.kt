package com.titicorp.titi.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titicorp.titi.model.SimpleProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadProducts()
        }
    }

    private suspend fun loadProducts() {
        val products = buildList {
            repeat(20) {
                add(
                    SimpleProduct(
                        id = "1",
                        title = "BMW",
                        price = 1500,
                        createdAt = 1682742243000,
                        thumbnail = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
                    )
                )
            }
        }
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