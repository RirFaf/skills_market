package android.skills_market.custom_composables

import android.skills_market.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "bell",
            modifier = Modifier
                .size(40.dp)
                .clickable {}
        )
        Text(text = "Профиль", fontSize = 28.sp)
        Image(
            painter = painterResource(id = R.drawable.tool),
            contentDescription = "tool",
            modifier = Modifier
                .size(40.dp)
                .clickable {}
        )
    }
}