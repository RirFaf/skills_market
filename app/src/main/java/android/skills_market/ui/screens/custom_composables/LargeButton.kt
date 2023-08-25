package android.skills_market.ui.screens.custom_composables

import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.DisabledAccentBlue
import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LargeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AccentBlue,
        disabledContainerColor = DisabledAccentBlue
    ),
    enabled: Boolean = true,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
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