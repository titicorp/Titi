package com.titicorp.titi.model

import androidx.compose.runtime.Immutable


@Immutable
data class SimpleUser(
    val id: String,
    val name: String,
    val avatar: String,
)