package ru.mperika.smartshoppinglist.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.mperika.smartshoppinglist.data.Product

class ShoppingListRepository(private val productDAO: ProductDAO) {

    val shoppingListItems: LiveData<MutableList<Product>> = productDAO.getAllProducts()

    @WorkerThread
    suspend fun insert(product: Product) {
        productDAO.insertProduct(product)
    }

}