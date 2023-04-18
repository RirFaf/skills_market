package android.skills_market.custom_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudPlace(heading: String, content: String) {
    Column(
        modifier = Modifier
            .padding(start = 14.dp, top = 14.dp)
            .fillMaxWidth()

    ) {
        Text(text = heading, color = Color.Gray, fontSize = 14.sp)
        Text(text = content, fontSize = 20.sp)
    }
}