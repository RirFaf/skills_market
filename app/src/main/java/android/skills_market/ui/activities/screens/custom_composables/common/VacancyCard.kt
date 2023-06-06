package android.skills_market.ui.activities.screens.custom_composables.common

import android.skills_market.R
import android.skills_market.data.VacancyModel
import android.skills_market.ui.theme.Gray150
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VacancyCard(vacancy: VacancyModel) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 14.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 0.dp
    ) {
        Column(
            Modifier
                .background(Gray150)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 14.dp)
        ) {
            Text(
                text = vacancy.title,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = vacancy.salary.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = vacancy.companyName,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            LargeButton(
                textResource = R.string.respond,
                onClick = { /*TODO*/ },
                height = 60)
        }
    }
}