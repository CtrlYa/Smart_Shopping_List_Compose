package ru.mperika.smartshoppinglist

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Date
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.data.ProductCategory
import ru.mperika.smartshoppinglist.ui.elements.DrawerAppComponent
import ru.mperika.smartshoppinglist.ui.elements.itemListCard
import ru.mperika.smartshoppinglist.ui.theme.SmartShoppinListTheme
import smartshoppinglist.R

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
fun MainComposable() {
    val productList = ArrayList<Product>()
    productList.add(
        Product(
            "Bread",
            "BradFactory",
            ProductCategory.MEAL,
            5,
            Date(10005000),
            ""
        )
    )
    productList.add(
        Product(
            "Butter",
            "ButterFactory",
            ProductCategory.MEAL,
            5,
            Date(10005000),
            ""
        )
    )
    productList.add(Product("Milk", "MilkFactory", ProductCategory.MEAL, 5, Date(10005000), ""))
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    SmartShoppinListTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    val context = LocalContext.current
                    IconButton(onClick = {
                        Toast.makeText(context, "Open side menu", Toast.LENGTH_SHORT)
                    }) {
                        Icon(Icons.Filled.Menu, "",
                            modifier = Modifier.clickable(onClick = {scaffoldState.drawerState}))
                    }
                },
                backgroundColor = Color.Gray
            )
        },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    val context = LocalContext.current
                    IconButton(onClick = {
                        Toast.makeText(context, "Pushed floating btn", Toast.LENGTH_SHORT).show()
                        //todo - open editor
                    }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                    }
                }
            }) {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "shoppingList") {
                        composable("shoppingList") { MessageList(productList) }
                    }
                }
            }
        }
    }
}

@Composable
fun MessageList(productList: List<Product>) {
    LazyColumn {
        items(productList) { product ->
            itemListCard(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainComposable()
}