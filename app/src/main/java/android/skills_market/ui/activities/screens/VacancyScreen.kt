package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.data.VacancyModel
import android.skills_market.ui.activities.screens.custom_composables.LargeButton
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.Inter
import android.skills_market.ui.theme.White
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VacancyScreen(navController: NavController, vacancy: VacancyModel) {
    Scaffold(
        modifier = Modifier.padding(vertical = 4.dp),
        topBar = { TopBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                Row(
                    Modifier
                        .height(90.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = vacancy.position,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Inter,
                        color = Color.Black
                    )
                }
                VacancyInfo(heading = "Отрасль", content = vacancy.edArea)
                Spacer(modifier = Modifier.padding(8.dp))
                VacancyInfo(heading = "Компания", content = vacancy.companyName)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Вакансия",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Inter,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.padding(6.dp))
                VacancyInfo(heading = "Тип занятости", content = vacancy.formOfEmployment)
                Spacer(modifier = Modifier.padding(8.dp))
                VacancyInfo(heading = "Требования", content = vacancy.requirements)
                Spacer(modifier = Modifier.padding(8.dp))
                VacancyInfo(heading = "Расположение", content = vacancy.location)
                Spacer(modifier = Modifier.padding(8.dp))
                VacancyInfo(heading = "Заработная плата", content = vacancy.salary.toString())
                Spacer(modifier = Modifier.padding(8.dp))
                VacancyInfo(heading = "О вакансии", content = vacancy.about)
                Spacer(modifier = Modifier.padding(8.dp))
                LargeButton(
                    text = stringResource(id = R.string.respond),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                )
            }
        }
    }
}


@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = "Понравившиеся", fontSize = 28.sp)
        IconButton(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Show menu",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun VacancyInfo(heading: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = heading, color = Color.Gray, fontSize = 14.sp)
        Text(text = content, fontSize = 20.sp)
    }
}