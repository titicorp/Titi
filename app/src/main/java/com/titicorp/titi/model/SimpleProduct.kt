package com.titicorp.titi.model

import androidx.compose.runtime.Immutable


@Immutable
data class SimpleProduct(
    val id: String,
    val title: String,
    val price: Long,
    val createdAt: Long,
    val thumbnail: String,
)
