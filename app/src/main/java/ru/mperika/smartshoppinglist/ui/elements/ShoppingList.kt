package ru.mperika.smartshoppinglist.ui.elements

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import ru.mperika.smartshoppinglist.data.view_models.ShoppingListViewModel
import ru.mperika.smartshoppinglist.db.AppDatabase

@Composable
fun ShoppingList() {
    val database = AppDatabase.getDatabase(LocalContext.current)
    val value = remember {
        //Следим за состоянием LiveData, обновляемой из базы
        ShoppingListViewModel(database.productDAO()).getLiveDataValues()
    }

    LazyColumn {
        items(value.value){product -> ItemCard().itemListCard(product = product)}
    }
}