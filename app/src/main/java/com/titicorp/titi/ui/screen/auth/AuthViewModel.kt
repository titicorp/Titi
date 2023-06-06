package com.titicorp.titi.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.titicorp.titi.network.TitiApi

class AuthViewModel : ViewModel() {

    private val api: TitiApi = TitiApi

    suspend fun login(phoneNumber: String, password: String): Boolean {
        return api.login(phoneNumber, password)
    }

    suspend fun register(name: String, phoneNumber: String, password: String): Boolean {
        return api.register(name, phoneNumber, password)
    }
}