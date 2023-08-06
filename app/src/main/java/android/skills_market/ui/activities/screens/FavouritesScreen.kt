package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.ui.activities.screens.custom_composables.VacancyCard
import android.skills_market.data.VacancyModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FavouritesScreen(navController: NavController) {
    androidx.compose.material3.Scaffold(
        modifier = Modifier.padding(),
        topBar = { TopBar() },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    itemsIndexed(
                        listOf(
                            VacancyModel(
                                position = "Интерн",
                                salary = 50000,
                                companyName = "Семейный доктор",
                                edArea = "Медицина",
                                formOfEmployment = "Частичная",
                                requirements = "Окончание 3 курса",
                                location = "Казань, метро Площадь Тукая"
                            ),
                            VacancyModel(
                                position = "Секретарь",
                                salary = 20000,
                                companyName = "ИП Петров Игорь Михайлович",
                                edArea = "Востоковедение",
                                formOfEmployment = "Частичная",
                                requirements = "Начальное знание турецкого языка",
                                location = "Казань, метро Козья слобода"
                            ),
                        )
                    ) { _, item ->
                        VacancyCard(vacancy = item, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                contentDescription = "Show menu",
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