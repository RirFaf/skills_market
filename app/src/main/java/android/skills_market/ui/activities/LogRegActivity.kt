package android.skills_market.ui.activities

import android.os.Bundle
import android.skills_market.ui.navigation.LogRegNavigationGraph
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class LogRegActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LogRegNavigationGraph(navController = navController)
        }
    }
}