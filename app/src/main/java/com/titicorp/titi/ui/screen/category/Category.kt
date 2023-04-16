package com.titicorp.titi.ui.screen.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.titicorp.titi.R

@Composable
fun Category(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation()
        Divider()
        LazyColumn {
            repeat(5) {
                item { CategoryItem() }
            }
        }
    }
}

@Composable
private fun TopNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Category",
            style = MaterialTheme.typography.h5,
        )
    }
}


@Composable
private fun CategoryItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp),
            painter = painterResource(R.drawable.screen_category),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Electronics",
            style = MaterialTheme.typography.subtitle1
        )
    }
}
