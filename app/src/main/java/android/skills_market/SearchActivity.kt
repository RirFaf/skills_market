package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.SearchBar
import android.skills_market.custom_composables.TabLayout
import android.skills_market.custom_composables.VacancyCard
import android.skills_market.dataclasses.Vacancy
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }
}

@Composable
private fun LoadUI() {
    Scaffold(
        topBar = { SearchBar()},
        bottomBar = { TabLayout() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .padding(0.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {
                itemsIndexed(
                    listOf(
                        Vacancy("1989", "vac", "vac", "10000000", "Kx", "L"),
                        Vacancy("1989", "vac", "vac", "10000000", "Kx", "L"),
                        Vacancy("1989", "vac", "vac", "10000000", "Kx", "L"),
                        Vacancy("1989", "vac", "vac", "10000000", "Kx", "L")
                    )
                ) { _, item ->
                    VacancyCard(vacancy = item)
                }
            }
        }
    }
}
