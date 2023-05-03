package android.skills_market.navigation.screens

import android.skills_market.R
import android.skills_market.custom_composables.LoginPasswordAndButton
import android.skills_market.navigation.common_classes.Screen
import android.skills_market.ui.theme.AccentBlue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    val localContext = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 160.dp, start = 20.dp, end = 20.dp),
        topBar = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_logo_dev_24),
                contentDescription = stringResource(id = R.string.logo_description),
                tint = AccentBlue
            )
            LoginPasswordAndButton(navController = navController)
        }
    }
}

