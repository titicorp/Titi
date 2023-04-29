package com.titicorp.titi.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.titicorp.titi.model.Product
import com.titicorp.titi.model.SimpleProduct
import com.titicorp.titi.model.SimpleUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val id: String,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadProductDetails()
        }
    }

    private suspend fun loadProductDetails() {
        val product = Product(
            id = id,
            title = "BMW",
            price = 1500,
            createdAt = 1682742243000,
            images = listOf(
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
            ),
            description = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
            user = SimpleUser(
                id = "1",
                avatar = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                name = "Emma Stone"
            ),
            moreFromUser = buildList {
                repeat(5) {
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
            },
            similarProducts = buildList {
                repeat(5) {
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
            },
        )
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