package com.taghavi.jetpackcomposeexample.material

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class DrawerAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DrawerAppComponent()
        }
    }

    @Composable
    fun DrawerAppComponent() {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val currentScreen = remember { mutableStateOf(DrawerAppScreen.Screen1) }
        val coroutineScope = rememberCoroutineScope()

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

    @Composable
    fun DrawerContentComponent(
        currentScreen: MutableState<DrawerAppScreen>,
        closeDrawer: () -> Unit,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            for (index in DrawerAppScreen.values().indices) {
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
                        Text(text = screen.name, modifier = Modifier.padding(16.dp))
                    }
                })
            }
        }
    }

    fun getScreenBasedOnIndex(index: Int) = when (index) {
        0 -> DrawerAppScreen.Screen1
        1 -> DrawerAppScreen.Screen2
        2 -> DrawerAppScreen.Screen3
        else -> DrawerAppScreen.Screen1
    }

    @Composable
    fun BodyContentComponent(
        currentScreen: DrawerAppScreen,
        openDrawer: () -> Unit,
    ) {
        when (currentScreen) {
            DrawerAppScreen.Screen1 -> Screen1Component(openDrawer)
            DrawerAppScreen.Screen2 -> Screen2Component(openDrawer)
            DrawerAppScreen.Screen3 -> Screen3Component(openDrawer)
        }
    }

    @Composable
    fun Screen1Component(openDrawer: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = { Text(text = "Screen 1 Title") }, navigationIcon = {
                IconButton(
                    onClick = openDrawer
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            })
            Surface(color = Color(0xFFFFD7D7.toInt()), modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(text = "Screen 1")
                    }
                )
            }
        }
    }

    @Composable
    fun Screen2Component(openDrawer: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = { Text(text = "Screen 2 Title") }, navigationIcon = {
                IconButton(
                    onClick = openDrawer
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            })
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
            TopAppBar(title = { Text(text = "Screen 3 Title") }, navigationIcon = {
                IconButton(
                    onClick = openDrawer
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            })
            Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(text = "Screen 3")
                    }
                )
            }
        }
    }


    enum class DrawerAppScreen {
        Screen1,
        Screen2,
        Screen3,
    }

    @Preview
    @Composable
    fun DrawerAppComponentPreview() {
        DrawerAppComponent()
    }
}