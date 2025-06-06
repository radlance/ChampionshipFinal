package com.radlance.championshipfinal.data.home

import com.radlance.championshipfinal.domain.home.FetchContent
import com.radlance.championshipfinal.domain.home.ProductRepository
import com.radlance.championshipfinal.domain.remote.FetchResult
import javax.inject.Inject

class LocalProductRepository @Inject constructor() : ProductRepository {
    override suspend fun fetchHomeContent(): FetchResult<FetchContent> {
        return try {
            FetchResult.Success(
                FetchContent(
                    categories = LocalStorage.categories,
                    products = LocalStorage.products
                )
            )
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override suspend fun addProductToCart(productId: Int): FetchResult<Int> {
        return try {
            LocalStorage.products = LocalStorage.products.map {
                if (it.id == productId) it.copy(quantityInCart = 1) else it
            }

            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun changeCartQuantity(productId: Int, quantity: Int): FetchResult<Int> {
        return try {
            LocalStorage.products = LocalStorage.products.map {
                if (it.id == productId) it.copy(quantityInCart = quantity) else it
            }

            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun removeProductFromCart(productId: Int): FetchResult<Int> {
        return try {
            LocalStorage.products = LocalStorage.products.map {
                if (it.id == productId) it.copy(quantityInCart = 0) else it
            }

            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }
}