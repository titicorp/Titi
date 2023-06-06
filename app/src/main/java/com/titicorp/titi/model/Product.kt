package com.titicorp.titi.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName


@Immutable
data class Product(
    val id: String,
    val title: String? = null,
    val price: Long,
    val createdAt: String,
    val description: String? = null,
    @SerializedName("presignedUrls")
    val images: List<String>,
    val user: SimpleUser? = null,
)
