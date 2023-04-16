package com.titicorp.titi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.titicorp.titi.ui.screen.Screen
import com.titicorp.titi.ui.screen.category.Category
import com.titicorp.titi.ui.screen.chats.Chats
import com.titicorp.titi.ui.screen.home.Home
import com.titicorp.titi.ui.screen.my.My
import com.titicorp.titi.ui.screen.product.Product
import com.titicorp.titi.ui.theme.TitiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TitiTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            Screen.Main.all.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(screen.icon),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.name)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.Main.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Main.Home.route) { Home(navController) }
                        composable(Screen.Main.Category.route) { Category(navController) }
                        composable(Screen.Main.Chats.route) { Chats(navController) }
                        composable(Screen.Main.My.route) { My(navController) }
                        composable(Screen.Product.route) { Product(navController)}
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TitiTheme {
            Greeting("Android")
        }
    }

}