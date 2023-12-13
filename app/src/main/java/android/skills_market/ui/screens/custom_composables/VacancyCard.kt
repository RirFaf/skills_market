package android.skills_market.ui.screens.custom_composables

import android.skills_market.R
import android.skills_market.network.models.ShortVacancyModel
import android.skills_market.ui.navigation.Screen
import android.skills_market.view_models.VacancyViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun VacancyCard(
    vacancy: ShortVacancyModel = ShortVacancyModel(0, "null", 10, "null"),
    navController: NavController
) {
//    val uiState = vacancyViewModel.uiState.collectAsState()
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                val vacancyViewModel = VacancyViewModel()
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
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 14.dp)
        ) {
            Text(
                text = vacancy.position,
                modifier = Modifier
                    .fillMaxWidth(),
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
            Button(
                onClick = {
//                    vacancyViewModel.respond()
                },
//                enabled = !uiState.value.isResponded
            ) {
                Text(text = stringResource(id = R.string.respond))
            }
        }
    }
}