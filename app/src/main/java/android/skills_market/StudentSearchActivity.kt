package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.ResumeCard
import android.skills_market.custom_composables.SearchBar
import android.skills_market.custom_composables.TabLayout
import android.skills_market.custom_composables.VacancyCard
import android.skills_market.dataclasses.Resume
import android.skills_market.dataclasses.Vacancy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class StudentSearchActivity : ComponentActivity() {
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
        topBar = { SearchBar() },
//        bottomBar = { TabLayout() }
    ) { modifier ->
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
                        Vacancy("fkanjjfjhhh", "JunDev", "Money", "130000", "Kazan", "AkBars"),
                        Vacancy("segbgsfbbgh", "SenDev", "Experience", "260000", "Arsk", "AkBars"),
                        Vacancy("syjdhgtnurj", "TeamLead", "Time", "1000000", "Moscow", "Yandex"),
                        Vacancy("iohnkhjbety", "Janitor", "Job", "13000", "Kazan", "Taif"),
                        Vacancy("ryyjfbxrjmh", "HR", "Work", "70000", "St.Petersburg", "Mail.ru"),
                        )
                ) { _, item ->
                    VacancyCard(vacancy = item)
                }
            }
        }
    }
}