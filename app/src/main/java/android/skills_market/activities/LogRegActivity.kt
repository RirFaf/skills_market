package android.skills_market.activities

import android.os.Bundle
import android.skills_market.network.AuthApiClient
import android.skills_market.network.SessionManager
import android.skills_market.ui.navigation.LogRegNavigationGraph
import android.skills_market.ui.theme.SkillsMarketTheme
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController

class LogRegActivity : ComponentActivity() {
    private lateinit var authApiClient: AuthApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authApiClient = AuthApiClient()

        setContent {
            val navController = rememberNavController()
            SkillsMarketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LogRegNavigationGraph(navController = navController)
                }
            }
        }
    }
}