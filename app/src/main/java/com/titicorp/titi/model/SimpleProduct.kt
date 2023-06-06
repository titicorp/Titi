package com.titicorp.titi.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName


@Immutable
data class SimpleProduct(
    val id: String,
    val title: String,
    val price: Long,
    val createdAt: Long,
    @SerializedName("coverImageUrl")
    val thumbnail: String,
    val favoriteCount: Int = 0,
    val messageCount: Int = 0,
    val seenCount: Int = 0,
)
