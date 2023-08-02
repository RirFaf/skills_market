package android.skills_market.ui.activities.screens.custom_composables

import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LargeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().height(60.dp),
    colors: ButtonColors
    ) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        shape = shapes.medium,
        colors = colors
    ) {
        Text(
            text = text,
            color = White,
            style = Typography.bodyMedium
        )
    }
}