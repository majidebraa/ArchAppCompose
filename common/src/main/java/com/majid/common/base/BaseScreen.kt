package com.majid.common.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.majid.navigation.NavigationCommand
import kotlinx.coroutines.launch

@Composable
fun BaseScreen(
    navController: NavHostController,
    viewModel: BaseViewModel,
    title: String = "ArchAppCompose",
    showBackArrow: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observe navigation commands
    val navigationCommands by viewModel.navigation.observeAsState()
    LaunchedEffect(navigationCommands) {
        navigationCommands?.getContentIfNotHandled()?.let { command ->
            when (command) {
                is NavigationCommand.To -> navController.navigate(command.directions)
                is NavigationCommand.Back -> navController.navigateUp()
                else -> {}
            }
        }
    }

    val context = LocalContext.current
    // Observe snackbar messages
    val snackBarError by viewModel.snackBarError.observeAsState()
    LaunchedEffect(snackBarError) {
        snackBarError?.getContentIfNotHandled()?.let { message ->
            scope.launch {
                snackbarHostState.showSnackbar(context.getString(message))
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            AppBar(
                title = title,
                showBackArrow = showBackArrow,
                navController = navController
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Make sure content starts after the app bar
            ) {
                content(paddingValues) // Pass the padding to the content composable
            }
        }
    )
}

