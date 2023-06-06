package com.titicorp.titi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.titicorp.titi.auth.UserManager
import com.titicorp.titi.ui.screen.Screen
import com.titicorp.titi.ui.screen.auth.login.Login
import com.titicorp.titi.ui.screen.auth.register.Register
import com.titicorp.titi.ui.screen.category.Category
import com.titicorp.titi.ui.screen.chats.Chats
import com.titicorp.titi.ui.screen.chats.newchat.NewChat
import com.titicorp.titi.ui.screen.home.Home
import com.titicorp.titi.ui.screen.my.My
import com.titicorp.titi.ui.screen.product.Product
import com.titicorp.titi.ui.screen.product.newproduct.NewProduct
import com.titicorp.titi.ui.theme.TitiTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TitiTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val isMainScreen = currentDestination?.route?.startsWith(Screen.Main.prefix) == true

                Scaffold(
                    bottomBar = {
                        if (isMainScreen) {
                            BottomNavigation {
                                val mainScreens = listOf(Screen.Main.Home, Screen.Main.Category, Screen.Main.Chats, Screen.Main.My)
                                mainScreens.forEach { screen ->
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
                    },
                    floatingActionButton = {
                        if (isMainScreen) {
                            FloatingActionButton(
                                onClick = { navController.navigate(Screen.NewProduct.route) }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.add),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    val loggedIn by remember {
                        mutableStateOf(UserManager.loggedIn)
                    }

                    val startDestination = if (loggedIn.not()) Screen.Auth.Login else Screen.Main.Home

                    NavHost(
                        navController,
                        startDestination = startDestination.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Auth.Login.route) { Login(navController) }
                        composable(Screen.Auth.Register.route) { Register(navController) }

                        composable(Screen.Main.Home.route) { Home(navController) }
                        composable(Screen.Main.Category.route) { Category(navController) }
                        composable(Screen.Main.Chats.route) { Chats(navController) }
                        composable(Screen.Main.My.route) { My(navController) }
                        composable(
                            route = Screen.Product.route,
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) {
                            Product(
                                navController = navController,
                                id = requireNotNull(it.arguments?.getString("id"))
                            )
                        }
                        composable(Screen.NewChat.route) { NewChat(navController) }
                        composable(Screen.NewProduct.route) { NewProduct(navController) }
                    }
                }
            }
        }
    }

}