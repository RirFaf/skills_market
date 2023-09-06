package android.skills_market.ui.activities

import android.os.Bundle
import android.skills_market.R
import android.skills_market.ui.navigation.LogRegNavigationGraph
import android.skills_market.ui.screens.custom_composables.LogRegTopBar
import android.skills_market.ui.theme.Black
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class LogRegActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogRegNavigationGraph(navController = navController)
            }
        }
    }
}