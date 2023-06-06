package com.titicorp.titi.network.model

sealed interface Auth {

    data class LoginRequest(
        val phoneNumber: String,
        val password: String,
    ) : Auth

    data class RegisterRequest(
        val name: String,
        val phoneNumber: String,
        val password: String,
    ) : Auth

    data class Respond(
        val authToken: String,
        val refreshToken: String,
    ) : Auth
}