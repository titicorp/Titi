package com.titicorp.titi.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.titicorp.titi.R
import com.titicorp.titi.auth.UserManager
import com.titicorp.titi.model.SimpleProduct
import com.titicorp.titi.ui.common.Loading
import com.titicorp.titi.utils.toReadableTime

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation(navController)
        Divider()

        val uiState by viewModel.uiState.collectAsState()

        when (val state = uiState) {
            is HomeViewModel.UiState.Loading -> Loading()
            is HomeViewModel.UiState.Content -> LazyColumn {
                items(state.products) { product ->
                    ProductItem(product) {
                        navController.navigate("product/${product.id}")
                    }
                }
            }
        }
    }
}

@Composable
private fun TopNavigation(
    navController: NavHostController,
) {
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
            Text(
                modifier = Modifier
                    .clickable {
                        UserManager.logout()
                        navController.navigate("login")
                    },
                text = "Dushanbe",
            )
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
    product: SimpleProduct,
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
            model = product.thumbnail,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = product.createdAt,
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp),
                text = "${product.price} c",
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