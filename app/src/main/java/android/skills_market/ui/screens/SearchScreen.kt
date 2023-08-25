package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.screens.custom_composables.SearchBar
import android.skills_market.ui.screens.custom_composables.VacancyCard
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
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
                                position = "Юрист",
                                salary = 50000,
                                companyName = "Газпром",
                                edArea = "Юриспрюденция",
                                formOfEmployment = "Полная",
                                requirements = "Знание английского языка",
                                location = "Казань, метро Площадь Тукая"
                            ),
                            VacancyModel(
                                position = "Программист",
                                salary = 30000,
                                companyName = "Битрикс 1С",
                                edArea = "Информационные Технологии",
                                formOfEmployment = "Частичная",
                                requirements = "Диплом об окончании курсов",
                                location = "Казань, метро Козья слобода"
                            ),
                            VacancyModel(
                                position = "Java Trainee",
                                salary = 60000,
                                companyName = "Aston",
                                edArea = "Информационные Технологии",
                                formOfEmployment = "Полный день",
                                requirements = "Знание английского языка",
                                location = "Казань, метро Площадь Тукая"
                            ),
                            VacancyModel(
                                position = "Android developer",
                                salary = 100000,
                                companyName = "Aston",
                                edArea = "Информационные технологии",
                                formOfEmployment = "Полная",
                                requirements = "Знание английского/немецкого языка",
                                location = "Казань, метро Суконная слобода"
                            ),
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
    Surface(
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_alt),
                    contentDescription = "filter",
                    modifier = Modifier.size(30.dp)
                )
            }
            SearchBar()
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "sort",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

