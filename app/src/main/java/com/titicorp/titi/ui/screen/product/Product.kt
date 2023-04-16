package com.titicorp.titi.ui.screen.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.titicorp.titi.R

@Composable
fun Product(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation(
            onClickBack = {
                navController.navigateUp()
            }
        )
        Divider()
        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f),
            columns = GridCells.Fixed(2)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Images()
                    UserDetails()
                    Divider()
                    Title()
                    CategoryAndTime()
                    Description()
                    Metas()
                    Divider()
                }
            }

            item(span = { GridItemSpan(2) }) { SectionLabel(name = "More from the same user") }
            repeat(5) {
                item {
                    SimilarProductItem {

                    }
                }
            }

            item(span = { GridItemSpan(2) }) { SectionLabel(name = "Similar products") }
            repeat(5) {
                item {
                    SimilarProductItem {

                    }
                }
            }
        }
        Divider()
        Price()
    }
}

@Composable
private fun TopNavigation(
    onClickBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = onClickBack),
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Images() {
    Box(
        modifier = Modifier
            .height(300.dp)
    ) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            pageCount = 5,
            state = pagerState,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Row(
            Modifier
                .height(30.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)

                )
            }
        }
    }
}

@Composable
private fun UserDetails() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            model = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Nigina Amonqulova",
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@Composable
private fun Title() {
    Text(
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 5.dp),
        text = "iPhone 11 Pro Max",
        style = MaterialTheme.typography.h6,
    )
}

@Composable
private fun CategoryAndTime() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        ProvideTextStyle(value = MaterialTheme.typography.caption) {
            Text(text = "Electronics")
            Text(text = " · ")
            Text(text = "15.04.2023")
        }
    }
}

@Composable
private fun Description() {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = LoremIpsum(50).values.first(),
        style = MaterialTheme.typography.body1,
    )
}

@Composable
private fun Metas() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        ProvideTextStyle(value = MaterialTheme.typography.overline) {
            Text(text = "Chats 2")
            Text(text = " · ")
            Text(text = "Loved 5")
            Text(text = " · ")
            Text(text = "Seen 203")
        }
    }
}

@Composable
private fun SectionLabel(name: String) {
    Text(
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp),
        text = name,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun SimilarProductItem(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = "https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM",
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = "iPhone 11 Pro Max",
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = "1500 c",
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
private fun Price() {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = {}),
            painter = painterResource(id = R.drawable.like),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f),
            text = "1500 c",
            style = MaterialTheme.typography.h6
        )

        Button(
            onClick = { }
        ) {
            Text(text = "Message")
        }
    }
}