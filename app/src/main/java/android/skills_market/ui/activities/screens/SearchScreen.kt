package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.ui.activities.screens.custom_composables.SearchBar
import android.skills_market.ui.activities.screens.custom_composables.VacancyCard
import android.skills_market.data.VacancyModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                            VacancyModel("JunDev", 10000, "AkBars"),
                            VacancyModel("SenDev", 10000, "AkBars"),
                            VacancyModel("TeamLead", 10000, "Yandex"),
                            VacancyModel("Janitor", 10000, "Taif"),
                            VacancyModel("HR", 10000, "Mail.ru"),
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
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                    contentDescription = "filter"
                )
            }
            SearchBar()
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_sort_24),
                    contentDescription = "sort"
                )
            }
        }
    }
}

