package android.skills_market.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumeButton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .padding(10.dp)
            .clickable {
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .clickable {
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Моё резюме", color = Color.White, fontSize = 20.sp)
        }
    }
}