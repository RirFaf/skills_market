package android.skills_market.custom_composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmployerVersionRow(
    employerVersion: Boolean,
    onEmployerVersionChanged:(Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.6f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Версия для работодателей",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = employerVersion,
            onCheckedChange = onEmployerVersionChanged,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.DarkGray,
                uncheckedThumbColor = Color.DarkGray,
                checkedTrackColor = Color.LightGray,
                uncheckedTrackColor = Color.Black,
                //Насыщенность цвета трэка
                checkedTrackAlpha = 0.3f,
                uncheckedTrackAlpha = 1.0f
            )
        )
    }
}