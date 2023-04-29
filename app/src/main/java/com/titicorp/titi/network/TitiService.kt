package com.titicorp.titi.network

import com.titicorp.titi.model.Product
import com.titicorp.titi.model.SimpleProduct
import retrofit2.http.GET
import retrofit2.http.Path

interface TitiService {

    @GET("/products")
    suspend fun getSimpleProducts(): List<SimpleProduct>

    @GET("/products/{id}")
    suspend fun getProductDetails(
        @Path("id") id: String
    ): Product

}