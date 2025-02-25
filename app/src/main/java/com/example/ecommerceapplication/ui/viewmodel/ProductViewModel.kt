package com.example.ecommerceapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ecommerceapplication.data.model.Product
import com.example.ecommerceapplication.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers

class ProductViewModel : ViewModel() {

    fun getProducts() = liveData(Dispatchers.IO) {
        val products = try {
            RetrofitClient.instance.getProducts()
        } catch (e: Exception) {
            emptyList<Product>()
        }
        emit(products)
    }

    fun getProduct(productId: Int) = liveData(Dispatchers.IO) {
        val product = try {
            RetrofitClient.instance.getProduct(productId)
        } catch (e: Exception) {
            null
        }
        emit(product)
    }

    fun getCategories() = liveData(Dispatchers.IO) {
        val categories = try {
            RetrofitClient.instance.getCategories()
        } catch (e: Exception) {
            emptyList<String>()
        }
        emit(categories)
    }

    fun getProductsByCategory(category: String) = liveData(Dispatchers.IO) {
        val products = try {
            RetrofitClient.instance.getProductsByCategory(category)
        } catch (e: Exception) {
            emptyList<Product>()
        }
        emit(products)
    }
}
