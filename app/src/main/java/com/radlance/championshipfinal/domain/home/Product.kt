package com.radlance.championshipfinal.domain.home

data class Product(
    val id: Int,
    val title: String,
    val categoryId: Int,
    val price: Int,
    val inCart: Boolean
)
