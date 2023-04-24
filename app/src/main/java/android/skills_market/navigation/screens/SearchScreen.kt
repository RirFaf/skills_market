package android.skills_market.navigation.screens

import android.skills_market.custom_composables.NavigationBar
import android.skills_market.custom_composables.SearchBar
import android.skills_market.custom_composables.VacancyCard
import android.skills_market.dataclasses.VacancyModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        topBar = { SearchBar() }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    itemsIndexed(
                        listOf(
                            VacancyModel("JunDev", 10000, "AkBars"),
                            VacancyModel("SenDev", 10000, "AkBars"),
                            VacancyModel("TeamLead", 10000, "Yandex"),
                            VacancyModel("Janitor", 10000, "Taif"),
                            VacancyModel("HR", 10000, "Mail.ru"),
                        )
                    ) { _, item ->
                        VacancyCard(vacancy = item)
                    }
                }
                Spacer(modifier = Modifier.padding(40.dp))
            }
        }
    }
}


