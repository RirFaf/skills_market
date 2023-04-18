package android.skills_market.custom_composables

import android.skills_market.R
import android.skills_market.dataclasses.VacancyModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreenResponsesRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        itemsIndexed(
            listOf(
                VacancyModel(R.drawable.apple, "Разработчик", "Akvelon"),
                VacancyModel(R.drawable.twitter, "Тестировщик", "Anderson")
            )
        ) { _, item ->
            ResponseCard(item)
        }
    }
}
