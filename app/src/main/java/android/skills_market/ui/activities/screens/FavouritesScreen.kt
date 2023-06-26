package android.skills_market.ui.activities.screens

import android.skills_market.custom_composables.SearchBar
import android.skills_market.ui.activities.screens.custom_composables.common.VacancyCard
import android.skills_market.data.VacancyModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FavouritesScreen() {
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(
                    listOf(
                        VacancyModel("SenDev", 10000, "AkBars"),
                        VacancyModel("TeamLead", 10000, "Yandex"),
                    )
                ) { _, item ->
                    VacancyCard(vacancy = item)
                }
            }
            Spacer(modifier = Modifier.padding(40.dp))
        }
    }
}
