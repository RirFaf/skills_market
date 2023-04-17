package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.SearchBar
import android.skills_market.custom_composables.TabLayout
import android.skills_market.custom_composables.VacancyCard
import android.skills_market.dataclasses.ResumeModel
import android.skills_market.dataclasses.VacancyModel
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
                        VacancyModel(R.drawable.apple, "JunDev", "AkBars"),
                        VacancyModel(R.drawable.bell, "SenDev", "AkBars"),
                        VacancyModel(R.drawable.logo, "TeamLead", "Yandex"),
                        VacancyModel(R.drawable.twitter, "Janitor", "Taif"),
                        VacancyModel(R.drawable.worker, "HR", "Mail.ru"),
                        )
                ) { _, item ->
                    VacancyCard(vacancy = item)
                }
            }
        }
    }
}