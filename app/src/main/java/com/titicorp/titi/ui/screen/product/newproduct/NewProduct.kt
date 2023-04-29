package com.titicorp.titi.ui.screen.product.newproduct

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.titicorp.titi.R
import com.titicorp.titi.ui.common.Loading
import com.titicorp.titi.ui.screen.Screen
import kotlinx.coroutines.launch


@Composable
fun NewProduct(
    navController: NavHostController,
    viewModel: NewProductViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopNavigation(
                onClickCancel = {
                    navController.navigateUp()
                },
                onClickPublish = {
                    scope.launch {
                        val id = viewModel.publish()
                        navController.navigate("product/$id") {
                            popUpTo(Screen.NewProduct.route) {
                                inclusive = true
                            }
                        }
                        navController.popBackStack(route = Screen.NewProduct.route, inclusive = false)
                    }
                }
            )
            Divider()
            Images(
                uiState.images
            ) {
                viewModel.onAddImage("https://fastly.picsum.photos/id/327/200/200.jpg?hmac=-qY8ApRJQJVHwDBxBmp-qnzM8xmqT5aJwHUXxZy3RAM")
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
            ) {
                Divider()
                Title(uiState.title) { viewModel.onTitleChange(it) }
                Divider()
                Category()
                Divider()
                Price(uiState.price) { viewModel.onPriceChange(it) }
                Divider()
                Description(uiState.description) { viewModel.onDescriptionChange(it) }
            }
        }
        if (uiState.isPublishing) {
            Dialog(
                onDismissRequest = {
                    viewModel.cancelPublishing()
                },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Loading()
            }
        }
        if (uiState.missingFields.isNotEmpty()) {
            MissingFields(uiState.missingFields) {
                viewModel.confirmMissingFields()
            }
        }
    }
}

@Composable
private fun TopNavigation(
    onClickCancel: () -> Unit,
    onClickPublish: () -> Unit,
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
                .size(34.dp)
                .clickable(onClick = onClickCancel),
            painter = painterResource(id = R.drawable.cancel),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            text = "New Product",
            style = MaterialTheme.typography.h5,
        )

        TextButton(onClick = onClickPublish) {
            Text(
                text = "Publish"
            )
        }
    }
}

@Composable
private fun Images(
    images: List<String>,
    onAddImage: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 30.dp, bottom = 20.dp),
    ) {
        NewImage(
            currentImageCount = images.size,
            onClick = onAddImage,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(images) {
                Image(it)
            }
        }
    }
}

@Composable
private fun NewImage(
    currentImageCount: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(80.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp),
            painter = painterResource(id = R.drawable.camera),
            contentDescription = null
        )

        Text(text = "$currentImageCount/10")
    }
}

@Composable
private fun Image(path: String) {
    AsyncImage(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp)),
        model = path,
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}


@Composable
private fun Title(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .padding(vertical = 20.dp),
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = "Enter title",
                    style = MaterialTheme.typography.body2
                )
            }
            innerTextField()
        }
    )
}

@Composable
private fun Category() {
    Row(
        modifier = Modifier
            .clickable(onClick = {})
            .padding(vertical = 20.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = "Select category",
            style = MaterialTheme.typography.subtitle1
        )
        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(id = R.drawable.arrow_forward),
            contentDescription = null
        )
    }
}

@Composable
private fun Price(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .padding(vertical = 20.dp),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = "Enter price",
                    style = MaterialTheme.typography.body2
                )
            }
            innerTextField()
        }
    )
}

@Composable
private fun Description(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = "Description of your product",
                    style = MaterialTheme.typography.body2
                )
            }
            innerTextField()
        }
    )
}

@Composable
private fun MissingFields(
    names: List<String>,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Button(onClick = onDismissRequest) {
            Text(text = "Fill missing fields (${names.joinToString(separator = ", ")})")
        }
    }
}