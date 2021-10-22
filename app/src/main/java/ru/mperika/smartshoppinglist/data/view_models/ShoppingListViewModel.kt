package ru.mperika.smartshoppinglist.data.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.db.ShoppingListRepository

class ShoppingListViewModel(private val repository: ShoppingListRepository) : ViewModel() {
    private var values = mutableListOf<Product>()

    private var data = repository.shoppingListItems
        get() = data

    fun addProduct(product: Product) {
        viewModelScope.launch { repository.insert(product) }
    }


}