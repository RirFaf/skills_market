package android.skills_market.activities

import android.os.Bundle
import android.skills_market.ui.screens.custom_composables.CustomNavBar
import android.skills_market.ui.navigation.NavigationGraph
import android.skills_market.ui.navigation.Screen
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            var showBottomBar by rememberSaveable { (mutableStateOf(true)) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            //Убираем нижнюю навигацию, когда заходим в переписку
            showBottomBar = when (navBackStackEntry?.destination?.route?.split(".")?.last()) {
                "MessengerScreen" -> {
                    false
                }

                else -> {
                    true
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