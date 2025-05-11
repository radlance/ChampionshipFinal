package com.radlance.championshipfinal.domain.home

import com.radlance.championshipfinal.domain.remote.FetchResult

interface ProductRepository {

    suspend fun fetchHomeContent(): FetchResult<FetchContent>

    suspend fun addProductToCart(productId: Int): FetchResult<Int>

    suspend fun changeCartQuantity(productId: Int, quantity: Int): FetchResult<Int>
}