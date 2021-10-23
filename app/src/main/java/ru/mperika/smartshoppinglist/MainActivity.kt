package ru.mperika.smartshoppinglist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.ui.elements.DrawerAppComponent
import ru.mperika.smartshoppinglist.ui.elements.ItemCard

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            MainComposable()
            DrawerAppComponent()
        }
    }
}

@Composable
fun MessageList(productList: List<Product>) {
    LazyColumn {
        items(productList) { product ->
            ItemCard().itemListCard(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}