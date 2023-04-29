package com.titicorp.titi.model

data class PublishableProduct(
    val userId: String,
    val title: String,
    val description: String,
    val price: Long,
    val images: List<String>,
)
