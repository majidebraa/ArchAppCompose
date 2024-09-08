package com.majid.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class CustomNavDirections(val route: String, val arguments: List<NamedNavArgument>) {

    object Home : CustomNavDirections("home", emptyList())

    data class Detail(val userId: String) : CustomNavDirections(
        route = "detail/$userId",
        arguments = listOf(
            navArgument("userId") {
                type = NavType.StringType
            }
        )
    )

    data class DetailImage(val imageUrl: String) : CustomNavDirections(
        route = "detailImage/$imageUrl",
        arguments = listOf(
            navArgument("imageUrl") {
                type = NavType.StringType
            }
        )
    )

    companion object {
        fun fromRoute(route: String?): CustomNavDirections? {
            return when {
                route == Home.route -> Home
                route?.startsWith("detail/") == true -> {
                    val userId = route.substringAfter("detail/")
                    Detail(userId)
                }
                route?.startsWith("detailImage/") == true -> {
                    val imageUrl = route.substringAfter("detailImage/")
                    Detail(imageUrl)
                }
                else -> null
            }
        }
    }
}
