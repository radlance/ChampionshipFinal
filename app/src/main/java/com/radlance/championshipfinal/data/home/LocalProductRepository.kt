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

    override suspend fun changeInCartStatus(productId: Int): FetchResult<Int> {
        return try {
            LocalStorage.products = LocalStorage.products.map {
                if (it.id == productId) it.copy(inCart = !it.inCart) else it
            }

            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }
}