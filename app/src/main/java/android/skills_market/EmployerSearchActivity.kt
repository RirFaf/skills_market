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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class EmployerSearchActivity : ComponentActivity() {
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
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(
                    listOf(
                        Resume("kjcffcjoajfk","Ринат","Зал"),
                        Resume("rsavdfghhrwg","Рома","Теннис"),
                        Resume("sghjrthdgfbg","Сергей","Штанга"),
                        Resume("fdfbdvdghhhr","Дима","Машина"),
                        Resume("tjdbgyjjjdnv","Азамат","Рахат-лукум"),
                        Resume("ilkyujfhgnfg","Рамиль","Андроид"),
                        Resume("zrhybkukbfyj","Ислам","Коран"),
                        Resume("itfbuyfuifnu","Карим","Кумбасар"),
                        Resume("dyvyubnirgjk","Рашид","Владивосток"),
                    )
                ) { _, item ->
                    ResumeCard(resume = item)
                }
            }
        }
    }
}
