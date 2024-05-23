package android.skills_market.ui.screens.custom_composables

import android.skills_market.data.constants.ResponseStatus
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyModel
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResponseCard(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onChatButtonClick: () -> Unit,
    onDelete: () -> Unit,
    vacancy: VacancyModel,
    status: String
) {
    val localContext = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val deleteButtonColor =
        if (isPressed) Color.Red else MaterialTheme.colorScheme.onPrimaryContainer
    OutlinedCard(
        modifier = modifier
            .clickable {
                onCardClick()
            },
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 14.dp)
        ) {
            Text(
                text = status,
                color = when (status) {
                    ResponseStatus.APPROVED -> Color.Green
                    ResponseStatus.DENIED -> Color.Red
                    ResponseStatus.SENT -> Color.Gray
                    else -> Color.Gray
                }
            )

            Text(
                text = vacancy.position,
                modifier = Modifier
                    .fillMaxWidth(),
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
                text = vacancy.company.name,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                            .show()
                        onChatButtonClick()
                    },
                ) {
                    Text(text = "Перейти в чат")
                }
                IconButton(
                    onClick = {
                        Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                            .show()
                        onDelete()
                    },
                    interactionSource = interactionSource,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = deleteButtonColor)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}