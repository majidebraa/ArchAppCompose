package com.majid.common.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.majid.common.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    showBackArrow: Boolean = false,
    navController: NavController? = null,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher), // replace with your logo drawable resource
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // App Name
                Text(text = title, fontSize = 18.sp)
            }
        },
        navigationIcon = (
                {
                    if(showBackArrow)
                        IconButton(onClick = {
                        navController?.popBackStack() // Navigate back when back arrow is clicked
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back_arrow), // replace with your back arrow drawable
                                contentDescription = "Back"
                            )
                        }
                }

             ),
        /*actions = {
            IconButton(onClick = {
                // Navigate to the profile screen
                navController?.navigate("profile")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile), // replace with your profile icon drawable
                    contentDescription = "Profile"
                )
            }
        },*/
        colors = topAppBarColors(
            containerColor = Color.Blue, // Background color
            navigationIconContentColor = Color.White, // Back arrow color
            titleContentColor = Color.White, // Title color
            actionIconContentColor = Color.White // Action (profile icon) color
        ),
        modifier = Modifier.fillMaxWidth()
    )
}