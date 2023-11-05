package android.skills_market.ui.screens.custom_composables

import android.skills_market.R
import android.skills_market.network.models.SelectedVacancyModel
import android.skills_market.network.models.ShortVacancyModel
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import android.skills_market.view_models.VacancyViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VacancyCard(vacancy: ShortVacancyModel, navController: NavController) {
    val vacancyViewModel = VacancyViewModel()
//    val uiState = vacancyViewModel.uiState.collectAsState()
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .clickable {
//                vacancyViewModel.setVacancy(vacancy)
//                navController.currentBackStackEntry?.savedStateHandle?.set(
//                    key = "vacancy",
//                    value = vacancyViewModel
//                )
                navController.navigate(Screen.VacancyScreen.route) {
                    /**
                     * Использовать чтобы предыдущий экран вылетил из бэкстека
                     */
//                    navController.graph.startDestinationRoute?.let { route ->
//                        popUpTo(route) {
//                            saveState = true
//                            inclusive = true
//                        }
//                    }
                    launchSingleTop = false
                    restoreState = true
                }
            },
        shape = shapes.medium,
        border = BorderStroke(width = 2.dp, color = AccentBlue)
    ) {
        Column(
            Modifier
                .background(White)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 14.dp)
        ) {
            Text(
                text = vacancy.position,
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = vacancy.salary.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = vacancy.companyName,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            LargeButton(
                text = stringResource(R.string.respond),
                onClick = {
//                    vacancyViewModel.respond()
                },
//                enabled = !uiState.value.isResponded
            )
        }
    }
}