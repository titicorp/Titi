package com.titicorp.titi.network

import com.titicorp.titi.model.Product
import com.titicorp.titi.model.PublishableProduct
import com.titicorp.titi.model.SimpleProduct
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TitiApi {

    private const val baseUrl = "http://34.172.23.234:8080"
    private const val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIsImV4cCI6MTY4NjEwMzQ5NywiaWF0IjoxNjg2MDE3MDk3fQ.t8ikkP977EhzsNglSDXg_sk0tkNsUw8InC_cOZ6AlS4"

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    chain.proceed(newRequest)
                }
            ).build()
    }

    private val service by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(TitiService::class.java)
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