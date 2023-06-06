package com.titicorp.titi.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName


@Immutable
data class SimpleProduct(
    val id: String,
    @SerializedName("description")
    val title: String,
    val price: Long,
    val createdAt: String,
    @SerializedName("coverImageUrl")
    val thumbnail: String,
)
