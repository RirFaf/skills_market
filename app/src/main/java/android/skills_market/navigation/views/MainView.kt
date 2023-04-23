package android.skills_market.navigation.views

import android.content.Context
import android.skills_market.custom_composables.NavigationBar
import android.skills_market.db_functions.SMFirebase
import android.skills_market.navigation.common_classes.NavigationGraph
import android.skills_market.navigation.common_classes.Screen
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreenView(localContext: Context) {
    val navController = rememberNavController()
    val database = SMFirebase()
    Scaffold(
        bottomBar = {
            if (database.isAuthenticated()) {
                NavigationBar(navController = navController)
            }
        }
    ) { modifier ->
        Column(modifier = Modifier) {
            NavigationGraph(localContext = localContext, navController = navController)
            if (database.isAuthenticated()) {
                navController.navigate(Screen.SearchScreen.route)
            }
        }
    }
}