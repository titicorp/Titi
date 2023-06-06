package com.titicorp.titi.network

import com.titicorp.titi.model.Product
import com.titicorp.titi.model.PublishableProduct
import com.titicorp.titi.network.model.SimpleProducts
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TitiService {

    @GET("/items")
    suspend fun getSimpleProducts(): SimpleProducts

    @GET("/item/{id}")
    suspend fun getProductDetails(
        @Path("id") id: String
    ): Product

    @POST("/items/new")
    suspend fun publishProduct(
        @Body product: PublishableProduct
    ): String

    @GET("/similarItems")
    suspend fun getSimilarItems(
        @Query("id") id: String
    ): SimpleProducts

    @GET("/moreItemsFromUser")
    suspend fun getMoreItemsFromUser(
        @Query("id") id: String
    ): SimpleProducts

}