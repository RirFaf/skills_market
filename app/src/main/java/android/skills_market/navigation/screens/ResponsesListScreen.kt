package android.skills_market.navigation.screens

import android.skills_market.custom_composables.NavigationBar
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ResponsesListScreen(navController: NavController) {
    Scaffold(
//        topBar =,
        bottomBar = { NavigationBar() }
    ) { modifier ->
        Column(modifier = Modifier) {

        }
    }
}


