package com.example.ecommerceapplication.data.network

import com.example.ecommerceapplication.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Product

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<Product>
}
