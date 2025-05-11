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
                    inCart = false,
                    price = PRODUCT_PRICE,
                    description = PRODUCT_DESCRIPTION,
                    materialCost = PRODUCT_MATERIAL_COST
                )
            }
        }
    }

    var products = createProducts(
        1 to 3,
        2 to 2,
        3 to 4
    )
}