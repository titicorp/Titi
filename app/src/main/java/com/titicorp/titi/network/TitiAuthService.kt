package com.titicorp.titi.network

import com.titicorp.titi.network.model.Auth
import retrofit2.http.Body
import retrofit2.http.POST

interface TitiAuthService {

    @POST("users/signin")
    suspend fun login(
        @Body loginRequest: Auth.LoginRequest,
    ): Auth.Respond

    @POST("users/signup")
    suspend fun register(
        @Body loginRequest: Auth.RegisterRequest,
    ): Auth.Respond

}