package com.titicorp.titi.model

import androidx.compose.runtime.Immutable


@Immutable
data class Product(
    val id: String,
    val title: String,
    val price: Long,
    val createdAt: Long,
    val description: String,
    val images: List<String>,
    val user: SimpleUser,
    val similarProducts: List<SimpleProduct>,
    val moreFromUser: List<SimpleProduct>,
)
