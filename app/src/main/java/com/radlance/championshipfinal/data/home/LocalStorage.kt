package com.radlance.championshipfinal.data.home

import com.radlance.championshipfinal.domain.home.Category
import com.radlance.championshipfinal.domain.home.Product

object LocalStorage {
    var categories = listOf(
        Category(id = 1, title = "Популярное"),
        Category(id = 2, title = "Женщинам"),
        Category(id = 3, title = "Мужчинам"),
        Category(id = 4, title = "Детям"),
        Category(id = 5, title = "Аксессуары")
    )

    var products = listOf(
        Product(
            id = 1,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 1,
            inCart = false,
            price = 300
        ),
        Product(
            id = 2,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 1,
            inCart = false,
            price = 300
        ),
        Product(
            id = 3,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 1,
            inCart = false,
            price = 300
        ),
        Product(
            id = 4,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 2,
            inCart = false,
            price = 300
        ),
        Product(
            id = 5,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 2,
            inCart = false,
            price = 300
        ),
        Product(
            id = 6,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 3,
            inCart = false,
            price = 300
        ),
        Product(
            id = 7,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 3,
            inCart = false,
            price = 300
        ),
        Product(
            id = 8,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 3,
            inCart = false,
            price = 300
        ),
        Product(
            id = 9,
            title = "Рубашка Воскресенье для машинного вязания",
            categoryId = 3,
            inCart = false,
            price = 300
        )
    )
}