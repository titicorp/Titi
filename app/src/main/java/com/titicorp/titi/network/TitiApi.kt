package com.titicorp.titi.network

import com.titicorp.titi.model.Product
import com.titicorp.titi.model.SimpleProduct
import com.titicorp.titi.model.SimpleUser
import kotlinx.coroutines.delay
import retrofit2.Retrofit


object TitiApi {

    private val service by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
        retrofit.create(TitiService::class.java)
    }

    suspend fun getSimpleProducts(): List<SimpleProduct> {
        delay(500)
        return buildList {
            repeat(20) {
                add(
                    SimpleProduct(
                        id = "1",
                        title = "BMW",
                        price = 1500,
                        createdAt = 1682742243000,
                        thumbnail = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
                    )
                )
            }
        }
    }

    suspend fun getProductDetails(id: String): Product {
        delay(500)
        return Product(
            id = id,
            title = "BMW",
            price = 1500,
            createdAt = 1682742243000,
            images = listOf(
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
            ),
            description = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
            user = SimpleUser(
                id = "1",
                avatar = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                name = "Emma Stone"
            ),
            moreFromUser = buildList {
                repeat(5) {
                    add(
                        SimpleProduct(
                            id = "1",
                            title = "BMW",
                            price = 1500,
                            createdAt = 1682742243000,
                            thumbnail = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
                        )
                    )
                }
            },
            similarProducts = buildList {
                repeat(5) {
                    add(
                        SimpleProduct(
                            id = "1",
                            title = "BMW",
                            price = 1500,
                            createdAt = 1682742243000,
                            thumbnail = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM"
                        )
                    )
                }
            },
        )
    }
}