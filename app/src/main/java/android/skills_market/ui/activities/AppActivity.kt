package android.skills_market.ui.activities

import android.os.Bundle
import android.skills_market.ui.screens.custom_composables.NavigationBar
import android.skills_market.ui.navigation.NavigationGraph
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
            Scaffold(
                bottomBar = {
                    if (bottomBarState.value) {
                        NavigationBar(navController = navController)
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