package com.majid.navigation

sealed class NavigationCommand {
    data class To(val directions: String) : NavigationCommand()
    data object Back : NavigationCommand()
}