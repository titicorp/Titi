package com.titicorp.titi.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.titicorp.titi.R

@Composable
fun Home(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation()
        Divider()
        LazyColumn {
            repeat(100) {
                item {
                    ProductItem {
                        navController.navigate("product")
                    }
                }
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
        Row(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(text = "Dushanbe")
            Icon(
                painter = painterResource(R.drawable.arrow_drop_down),
                contentDescription = null
            )
        }
        Icon(
            painter = painterResource(R.drawable.search),
            contentDescription = null
        )
    }
}

@Composable
fun ProductItem(
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
        ) {
            Text(
                text = "iPhone 11 Pro Max",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "15.04.2023",
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp),
                text = "1500 c",
                style = MaterialTheme.typography.h6
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.Bottom),
        ) {
            Icon(
                modifier = Modifier
                    .size(15.dp),
                painter = painterResource(R.drawable.chat),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "2",
                style = MaterialTheme.typography.overline,
            )
        }
    }
}