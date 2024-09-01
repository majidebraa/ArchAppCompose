package com.majid.detail

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.majid.common.base.BaseScreen

@Composable
fun DetailScreen(userId: String?, viewModel: DetailViewModel, navController: NavHostController) {
    BaseScreen(viewModel = viewModel, navController = navController) {

        // Your StartScreen content here
        Button(onClick = {
           //viewModel.navigate(NavigationCommand.Back)
        }) {
            Text("Go to Details")
        }
    }
}