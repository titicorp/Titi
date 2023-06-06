package com.titicorp.titi.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserManager {

    private lateinit var sharedPreferences: SharedPreferences
    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"

    var loggedIn: Boolean = false
        private set

    val authToken: String get() = requireNotNull(sharedPreferences.getString(KEY_AUTH_TOKEN, null))
    val refreshToken: String get() = requireNotNull(sharedPreferences.getString(KEY_REFRESH_TOKEN, null))

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        loggedIn = sharedPreferences.contains(KEY_AUTH_TOKEN)
    }

    fun login(
        authToken: String,
        refreshToken: String
    ) {
        sharedPreferences.edit {
            putString(KEY_AUTH_TOKEN, authToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
        }
    }

    fun logout() {
        sharedPreferences.edit {
            remove(KEY_AUTH_TOKEN)
            remove(KEY_REFRESH_TOKEN)
        }
        loggedIn = false
    }

}