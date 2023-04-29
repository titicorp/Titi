package com.titicorp.titi.ui.screen.my

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun My(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation()
        Divider()
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
            modifier = Modifier
                .weight(1f),
            text = "My titi",
            style = MaterialTheme.typography.h5,
        )

        Icon(
            modifier = Modifier
                .size(30.dp)
                .clickable(onClick = {}),
            painter = painterResource(id = R.drawable.settings),
            contentDescription = null
        )
    }
}
