package com.titicorp.titi.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName


@Immutable
data class SimpleUser(
    val id: String,
    val name: String,
    @SerializedName("imageUrl")
    val avatar: String,
)