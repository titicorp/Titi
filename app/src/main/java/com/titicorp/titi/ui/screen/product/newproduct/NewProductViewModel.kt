package com.titicorp.titi.ui.screen.product.newproduct

import androidx.lifecycle.ViewModel
import com.titicorp.titi.model.PublishableProduct
import com.titicorp.titi.network.TitiApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class NewProductViewModel(
    private val api: TitiApi = TitiApi,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            title = "",
            description = "",
            price = "",
            images = emptyList()
        )
    )
    val uiState: StateFlow<UiState> = _uiState

    fun onTitleChange(value: String) {
        _uiState.update {
            it.copy(title = value)
        }
    }

    fun onPriceChange(value: String) {
        _uiState.update {
            it.copy(price = value)
        }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update {
            it.copy(description = value)
        }
    }

    fun onAddImage(path: String) {
        _uiState.update {
            it.copy(
                images = buildList {
                    addAll(it.images)
                    add(path)
                }
            )
        }
    }

    suspend fun publish(): String? = withContext(Dispatchers.IO) {
        val uiState = uiState.value
        val missingFields = listOfNotNull(
            if (uiState.title.isEmpty()) "Title" else null,
            if (uiState.price.isEmpty()) "Price" else null,
            if (uiState.description.isEmpty()) "Description" else null,
            if (uiState.images.isEmpty()) "Images" else null,

            )
        if (missingFields.isNotEmpty()) {
            _uiState.update {
                it.copy(missingFields = missingFields)
            }
            return@withContext null
        }
        _uiState.update {
            it.copy(isPublishing = true)
        }
        api.publishProduct(
            product = PublishableProduct(
                userId = "user001",
                title = uiState.title,
                description = uiState.description,
                price = uiState.price.toLong(),
                images = uiState.images
            )
        )
    }

    fun confirmMissingFields() {
        _uiState.update {
            it.copy(missingFields = emptyList())
        }
    }

    fun cancelPublishing() {
        _uiState.update {
            it.copy(isPublishing = false)
        }
    }

    data class UiState(
        val title: String,
        val description: String,
        val price: String,
        val images: List<String>,
        val isPublishing: Boolean = false,
        val missingFields: List<String> = emptyList(),
    )
}