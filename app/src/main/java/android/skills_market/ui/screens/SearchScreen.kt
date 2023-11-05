package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.screens.custom_composables.SearchBar
import android.skills_market.ui.screens.custom_composables.VacancyCard
import android.skills_market.network.models.SelectedVacancyModel
import android.skills_market.network.models.ShortVacancyModel
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
fun SearchScreen(navController: NavController, vacancies: ArrayList<ShortVacancyModel>) {
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
                    itemsIndexed(vacancies) { _, vacancy ->
                        VacancyCard(vacancy = vacancy, navController = navController)
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

