package android.skills_market.custom_composables

import android.skills_market.R
import android.skills_market.dataclasses.ResponseModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                ResponseModel(R.drawable.apple, "Разработчик", "Akvelon"),
                ResponseModel(R.drawable.twitter, "Тестировщик", "Anderson")
            )
        ) { _, item ->
            ResponseCard(item)
        }
    }
}
