package android.skills_market.activities

import android.os.Bundle
import android.skills_market.data.network.AuthApiClient
import android.skills_market.ui.screens.custom_composables.CustomNavBar
import android.skills_market.ui.navigation.NavigationGraph
import android.skills_market.ui.theme.SkillsMarketTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class AppActivity : ComponentActivity() {
    private lateinit var authApiClient: AuthApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authApiClient = AuthApiClient()

        setContent {
            var showBottomBar by rememberSaveable { (mutableStateOf(true)) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            //Убираем нижнюю навигацию, когда заходим в переписку
            when (navBackStackEntry?.destination?.route) {
                "search_screen" -> {
                    showBottomBar = true
                }

                "vacancy_screen" -> {
                    showBottomBar = true
                }

                "favourites_screen" -> {
                    showBottomBar = true
                }

                "responses_list_screen" -> {
                    showBottomBar = true
                }

                "chat_list_screen" -> {
                    showBottomBar = true
                }

                "messenger_screen" -> {
                    showBottomBar = false
                }
            }
            SkillsMarketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Scaffold(
                        bottomBar = {
                            if (showBottomBar) {
                                CustomNavBar(navController = navController)
                            }
                        }
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            NavigationGraph(navController)
                        }
                    }
                }
            }
        }
    }
}