package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.data.VacancyModel
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.AccentBlue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun VacancyScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.padding(vertical = 4.dp),
        topBar = { TopBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            Row() {

            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "back",
                tint = AccentBlue
            )
        }
    }
}
