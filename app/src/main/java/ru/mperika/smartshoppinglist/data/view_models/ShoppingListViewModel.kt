package ru.mperika.smartshoppinglist.data.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.db.ProductDAO

class ShoppingListViewModel(private val repository: ProductDAO) : ViewModel() {
    private var values = mutableListOf<Product>()
    private val liveDataValues: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>().also { loadProducts() }
    }

    private var data = repository.getAllProducts()
        get() = data

    fun addProduct(product: Product) {
        viewModelScope.launch { repository.insertProduct(product) }
    }

    private fun loadProducts() {
        viewModelScope.launch { repository.getAllProducts() }
    }

    fun getLiveDataValues(): LiveData<List<Product>> {
        return liveDataValues
    }

}