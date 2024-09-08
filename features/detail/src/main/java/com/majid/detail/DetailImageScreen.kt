package com.majid.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

import com.majid.common.base.BaseScreen

@Composable
fun DetailImageScreen(viewModel: DetailImageViewModel, navController: NavHostController) {
    BaseScreen(viewModel = viewModel, navController = navController, showBackArrow = true) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = viewModel.imageUrl.collectAsState().value),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds() // Ensures the image fills the whole screen
            )
        }

    }
}