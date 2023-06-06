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
            route = "${prefix}_home",
            name = R.string.screen_home,
            icon = R.drawable.screen_home,
        )

        object Category : Main(
            route = "${prefix}_category",
            name = R.string.screen_category,
            icon = R.drawable.screen_category,
        )

        object Chats : Main(
            route = "${prefix}_chats",
            name = R.string.screen_chats,
            icon = R.drawable.screen_chat,
        )

        object My : Main(
            route = "${prefix}_my",
            name = R.string.screen_my,
            icon = R.drawable.screen_my,
        )

        companion object {
            const val prefix = "main"
        }
    }

    object Product : Screen("product/{id}")

    object NewChat : Screen("new_chat")

    object NewProduct : Screen("new_product")

    sealed class Auth(route: String) : Screen(route) {
        object Login : Auth("login")
        object Register : Auth("register")
    }
}