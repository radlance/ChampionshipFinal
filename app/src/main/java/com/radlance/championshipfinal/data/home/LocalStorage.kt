package com.radlance.championshipfinal.data.home

import com.radlance.championshipfinal.domain.home.Category
import com.radlance.championshipfinal.domain.home.Product
import com.radlance.championshipfinal.domain.project.Project
import java.time.LocalDate

object LocalStorage {
    val categories = listOf(
        Category(id = 1, title = "Популярные"),
        Category(id = 2, title = "Женщинам"),
        Category(id = 3, title = "Мужчинам"),
        Category(id = 4, title = "Детям"),
        Category(id = 5, title = "Аксессуары")
    )

    private const val PRODUCT_TITLE = "Рубашка Воскресенье для машинного вязания"
    private const val PRODUCT_DESCRIPTION = "Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку.\n" +
            "Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько.\n" +
            "Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г.\n" +
            "Артикулы, из которых мы вязали эту модель: Zermatt Zegna Baruffa, Cashfive, Baby Cashmere Loro Piana, Soft Donegal и другие.\n" +
            "Примерный расход на шапку с подгибом 70-90г."
    private const val PRODUCT_MATERIAL_COST = "80-90 г"
    private const val PRODUCT_PRICE = 300

    private fun createProducts(vararg categoryPairs: Pair<Int, Int>): List<Product> {
        var idCounter = 1
        return categoryPairs.flatMap { (categoryId, count) ->
            List(count) {
                Product(
                    id = idCounter++,
                    title = PRODUCT_TITLE,
                    categoryId = categoryId,
                    quantityInCart = 0,
                    price = PRODUCT_PRICE,
                    description = PRODUCT_DESCRIPTION,
                    materialCost = PRODUCT_MATERIAL_COST
                )
            }
        }
    }

    var products: List<Product> = createProducts(
        1 to 7,
        2 to 2,
        3 to 4
    )

    val projects = mutableListOf(
        Project(
            id = 1,
            type = "some type",
            name = "Мой первый проект",
            startDate = LocalDate.of(2025, 5, 8),
            endDate = null,
            toWhom = "someone",
            descriptionSource = "some description",
            category = "some category"
        ),
        Project(
            id = 2,
            type = "some type",
            name = "Мой второй проект",
            startDate = LocalDate.of(2025, 5, 9),
            endDate = null,
            toWhom = "someone",
            descriptionSource = "some description",
            category = "some category"
        ),
        Project(
            id = 3,
            type = "some type",
            name = "Мой третий проект",
            startDate = LocalDate.of(2025, 5, 10),
            endDate = null,
            toWhom = "someone",
            descriptionSource = "some description",
            category = "some category"
        ),
        Project(
            id = 4,
            type = "some type",
            name = "Мой четвертый проект",
            startDate = LocalDate.of(2025, 5, 11),
            endDate = null,
            toWhom = "someone",
            descriptionSource = "some description",
            category = "some category"
        )
    )
}