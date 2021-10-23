package ru.mperika.smartshoppinglist.ui.elements

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.mperika.smartshoppinglist.ui.theme.SmartShoppinListTheme

@Composable
fun DrawerAppComponent() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentScreen = remember { mutableStateOf(DrawerScreen.Screen1) }
    val coroutineScope = rememberCoroutineScope()
    SmartShoppinListTheme {
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                DrawerContentComponent(
                    currentScreen = currentScreen,
                    closeDrawer = { coroutineScope.launch { drawerState.close() } }
                )
            },
            content = {
                BodyContentComponent(
                    currentScreen = currentScreen.value,
                    openDrawer = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            }
        )
    }
}

@Composable
fun DrawerContentComponent(
    currentScreen: MutableState<DrawerScreen>,
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (index in DrawerScreen.values().indices) {
            val screen = getScreenBasedOnIndex(index)
            Column(Modifier.clickable(onClick = {
                currentScreen.value = screen
                closeDrawer()
            }), content = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = if (currentScreen.value == screen) {
                        MaterialTheme.colors.secondary
                    } else {
                        MaterialTheme.colors.surface
                    }
                ) {
                    Text(text = screen.getText(), modifier = Modifier.padding(16.dp))
                }
            })
        }
    }
}

/**
 * Returns the corresponding DrawerAppScreen based on the index passed to it.
 */
fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> DrawerScreen.Screen1
    1 -> DrawerScreen.Screen2
    2 -> DrawerScreen.Screen3
    else -> DrawerScreen.Screen1
}

/**
 * Passed the corresponding screen composable based on the current screen that's active.
 */
@Composable
fun BodyContentComponent(
    currentScreen: DrawerScreen,
    openDrawer: () -> Unit
) {
    when (currentScreen) {
        DrawerScreen.Screen1 -> Screen1Component(
            openDrawer
        )
        DrawerScreen.Screen2 -> Screen2Component(
            openDrawer
        )
        DrawerScreen.Screen3 -> Screen3Component(
            openDrawer
        )
    }
}

@Composable
fun Screen1Component(openDrawer: () -> Unit) {
//    val productList: List<Product> by ShoppingListViewModel().getLiveDataVals()
//    val mutableState = remember {
//        ShoppingListViewModel().getLiveDataVals()
//    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(DrawerScreen.Screen1.getText()) },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
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
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(color = MaterialTheme.colors.background) {
                Column {
//                    MessageList(productList)
                }
            }
        }
    }
}

@Composable
fun Screen2Component(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(DrawerScreen.Screen2.getText()) },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFffe9d6.toInt()), modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Screen 2")
                }
            )
        }
    }
}

@Composable
fun Screen3Component(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(DrawerScreen.Screen3.getText()) },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Screen 3")
                }
            )
        }
    }
}

enum class DrawerScreen(text: String) {
    Screen1("Список покупок"),
    Screen2("Холодильник"),
    Screen3("Настройки");

    private var text:String = text
    fun getText(): String {
        return text
    }
}

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Preview
@Composable
fun DrawerAppComponentPreview() {
    DrawerAppComponent()
}