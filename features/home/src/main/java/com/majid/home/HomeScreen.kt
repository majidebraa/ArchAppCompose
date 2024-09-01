package com.majid.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.majid.common.base.BaseScreen
import com.majid.domain.utils.Resource
import com.majid.model.User
import com.majid.navigation.CustomNavDirections


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    BaseScreen(viewModel = viewModel, navController = navController){
        // Your StartScreen content here
        /*Button(onClick = {
           viewModel.navigate(CustomNavDirections.Detail)
        }) {
            Text("Go to Details")
        }*/
        val usersResource by viewModel.users.collectAsState()

        when (usersResource) {
            is Resource.Success -> {
                val users = (usersResource as Resource.Success<List<User>>).data
                LazyColumn {
                    items(users) { user ->
                        UserItem(user = user, onClick = {
                            user.login?.let { login ->
                                // navController.navigate(CustomNavDirections.Detail(user.login!!).route)
                                viewModel.userClicksOnItem(user = user)
                            }
                        })
                    }
                }
            }
            is Resource.Error -> {
                // Show error state
            }
            is Resource.Loading -> {
                // Show loading state
            }
        }
    }
}


@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Assuming you have a way to load the image from a URL
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )

        user.login?.let {
            Text(
                text = it,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}