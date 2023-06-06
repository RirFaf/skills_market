package android.skills_market.ui.activities.screens.custom_composables.common

import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Typography
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun LargeButton(
    @StringRes textResource: Int,
    onClick: () -> Unit,
    height: Int,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
        shape = shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = Black)
    ) {
        Text(
            text = stringResource(id = textResource),
            style = Typography.bodyMedium
        )
    }
}