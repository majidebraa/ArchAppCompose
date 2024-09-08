package com.majid.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.majid.common.base.BaseScreen
import com.majid.details.R
import com.majid.domain.utils.Resource
import com.majid.model.User

@Composable
fun DetailScreen(/*userId: String?,*/ viewModel: DetailViewModel, navController: NavHostController) {
    BaseScreen(viewModel = viewModel, navController = navController, showBackArrow = true) {

        val userResource by viewModel.user.collectAsState() // If using StateFlow or LiveData
        var isRefreshing by remember { mutableStateOf(false) }

        //val scaffoldState = rememberScaffoldState()
        //val coroutineScope = rememberCoroutineScope()
        when (userResource) {
            is Resource.Success -> {
                val user = (userResource as Resource.Success<User>).data
                SwipeRefresh(
                    state = SwipeRefreshState(isRefreshing),
                    onRefresh = {
                        isRefreshing = true
                        viewModel.reloadDataWhenUserRefreshes()
                        isRefreshing = false
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(0.dp)
                        ) {
                            // Background Image
                            Image(
                                painter = painterResource(id = R.drawable.bg_android),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )

                            Spacer(modifier = Modifier.height(56.dp)) // Extra space to adjust for the avatar

                            // User Info Section
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Icon(Icons.Filled.AccountBox, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = user.name ?: "", fontSize = 18.sp)
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Icon(painterResource(id = R.drawable.ic_account_balance_black_24dp), contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = user.company ?: "")
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Icon(painterResource(id = R.drawable.ic_language_black_24dp), contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = user.blog ?: "")
                            }
                        }

                        // Avatar Image (centered at the bottom of the background)
                        Image(
                            painter = rememberAsyncImagePainter(model = user.avatarUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.TopCenter) // Align at the bottom center of the background
                                .offset(y = 160.dp) // Adjust the offset to place it at the bottom of the background
                                .clip(CircleShape)
                                .clickable { viewModel.userClicksOnAvatarImage(user) }
                        )
                    }
                }
            }
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is Resource.Error -> TODO()
        }

    }
}