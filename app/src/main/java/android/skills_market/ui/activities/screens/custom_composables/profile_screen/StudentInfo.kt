package android.skills_market.custom_composables

import android.skills_market.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentInfo(name: String, number: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.25f)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            val borderWidth = 2.dp
            Image(
                painter = painterResource(id = R.drawable.worker),
                contentDescription = "worker",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(borderWidth, color = Color.Black),
                        CircleShape
                    )
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = name, fontSize = 25.sp)
                Text(text = number, fontSize = 18.sp)
            }
        }
    }
}