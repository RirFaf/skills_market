package android.skills_market.activities

import android.os.Bundle
import android.skills_market.ui.screens.custom_composables.CustomNavBar
import android.skills_market.ui.navigation.NavigationGraph
import android.skills_market.ui.theme.SkillsMarketTheme
import android.skills_market.view_model.LoginViewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            //Убираем нижнюю навигацию, когда заходим в переписку
            when (navBackStackEntry?.destination?.route) {
                "search_screen" -> {
                    bottomBarState.value = true
                }

                "vacancy_screen" -> {
                    bottomBarState.value = true
                }

                "favourites_screen" -> {
                    bottomBarState.value = true
                }

                "responses_list_screen" -> {
                    bottomBarState.value = true
                }

                "chat_list_screen" -> {
                    bottomBarState.value = true
                }

                "messenger_screen" -> {
                    bottomBarState.value = false
                }
            }
            SkillsMarketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            if (bottomBarState.value) {
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