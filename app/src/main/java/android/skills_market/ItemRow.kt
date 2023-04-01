package android.skills_market

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
fun ItemRow(item: ResponsesModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.25f)
            .padding(10.dp),

        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp

    )
    {
        Box() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = "employer",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(140.dp)
                        .clip(CircleShape)
                )
                Text(text = item.title)
            }

        Button(onClick = { /*TODO*/ },
                colors=ButtonDefaults.buttonColors(backgroundColor = Color.Black)) {
            Text(text="Принять")

        }}
    }

}


