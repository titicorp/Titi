package com.titicorp.titi.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName


@Immutable
data class Product(
    val id: String,
    val title: String? = null,
    val price: Long,
    val createdAt: Long,
    val description: String? = null,
    @SerializedName("presignedUrls")
    val images: List<String>,
    val owner: SimpleUser? = null,
    val favoriteCount: Int = 0,
    val messageCount: Int = 0,
    val seenCount: Int = 0,
)
