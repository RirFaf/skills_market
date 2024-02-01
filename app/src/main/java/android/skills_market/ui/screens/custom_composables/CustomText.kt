package android.skills_market.ui.screens.custom_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun CustomText(heading: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = heading, color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 14.sp)
        Text(text = content, fontSize = 20.sp)
    }
}