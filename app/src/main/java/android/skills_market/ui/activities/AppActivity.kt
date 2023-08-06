package android.skills_market.ui.activities

import android.os.Bundle
import android.skills_market.ui.activities.screens.custom_composables.NavigationBar
import android.skills_market.ui.navigation.NavigationGraph
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { NavigationBar(navController = navController) }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}