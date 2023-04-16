package com.titicorp.titi.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.titicorp.titi.R

sealed class Screen(
    val route: String,
) {

    sealed class Main(
        route: String,
        @StringRes val name: Int,
        @DrawableRes val icon: Int,
    ) : Screen(route) {
        object Home : Main(
            route = "home",
            name = R.string.screen_home,
            icon = R.drawable.screen_home,
        )

        object Category : Main(
            route = "category",
            name = R.string.screen_category,
            icon = R.drawable.screen_category,
        )

        object Chats : Main(
            route = "chats",
            name = R.string.screen_chats,
            icon = R.drawable.screen_chat,
        )

        object My : Main(
            route = "my",
            name = R.string.screen_my,
            icon = R.drawable.screen_my,
        )

        companion object {
            val all = listOf(Home, Category, Chats, My)
        }
    }

    object Product: Screen("product")

}