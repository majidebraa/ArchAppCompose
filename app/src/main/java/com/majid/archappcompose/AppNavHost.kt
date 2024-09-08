package com.majid.archappcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.majid.detail.DetailImageScreen
import com.majid.detail.DetailImageViewModel
import com.majid.detail.DetailScreen
import com.majid.detail.DetailViewModel
import com.majid.home.HomeScreen
import com.majid.home.HomeViewModel
import com.majid.navigation.CustomNavDirections
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost(navController: NavHostController= rememberNavController(), startDestination: String = CustomNavDirections.Home.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(CustomNavDirections.Home.route) {
            val viewModel: HomeViewModel = koinViewModel()
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "detail/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val viewModel: DetailViewModel = koinViewModel(
                parameters = { parametersOf(userId) }
            )
            DetailScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "detailImage/{imageUrl}",
            arguments = listOf(navArgument("imageUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")
            val viewModel: DetailImageViewModel = koinViewModel(
                parameters = { parametersOf(imageUrl) }
            )
            DetailImageScreen(viewModel = viewModel, navController = navController)
        }
    }
}