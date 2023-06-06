package com.titicorp.titi.network

import com.titicorp.titi.auth.UserManager
import com.titicorp.titi.model.Product
import com.titicorp.titi.model.PublishableProduct
import com.titicorp.titi.model.SimpleProduct
import com.titicorp.titi.network.model.Auth
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TitiApi {

    private const val baseUrl = "http://35.193.174.186:8080"
    private val userManager = UserManager

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${userManager.authToken}")
                        .build()
                    chain.proceed(newRequest)
                }
            ).build()
    }

    private val authService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(TitiAuthService::class.java)
    }

    private val service by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(TitiService::class.java)
    }

    suspend fun login(phoneNumber: String, password: String): Boolean {
        val result = authService.login(
            Auth.LoginRequest(
                phoneNumber = phoneNumber,
                password = password
            )
        )
        userManager.login(
            authToken = result.authToken,
            refreshToken = result.refreshToken
        )
        return true
    }

    suspend fun register(name: String, phoneNumber: String, password: String): Boolean {
        val result = authService.register(
            Auth.RegisterRequest(
                name = name,
                phoneNumber = phoneNumber,
                password = password
            )
        )
        userManager.login(
            authToken = result.authToken,
            refreshToken = result.refreshToken
        )
        return true
    }

    suspend fun getSimpleProducts(): List<SimpleProduct> {
        return service.getSimpleProducts().items
    }

    suspend fun getProductDetails(id: String): Product {
        return service.getProductDetails(id)
    }

    suspend fun getSimilarItems(id: String): List<SimpleProduct> {
        return service.getSimpleProducts().items
    }

    suspend fun getMoreItemsFromUser(id: String): List<SimpleProduct> {
        return service.getSimpleProducts().items
    }

    suspend fun publishProduct(product: PublishableProduct): String {
        delay(500)
        return "1"
    }
}