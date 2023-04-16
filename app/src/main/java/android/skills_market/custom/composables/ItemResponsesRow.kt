package android.skills_market.custom.composables
import android.skills_market.dataclasses.ResponsesModel
import androidx.compose.foundation.*
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
fun ItemResponsesRow(item: ResponsesModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp

    ) {


        Column() {

            Box(modifier = Modifier.background(color= Color.White))  {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val borderWidth = 2.dp
                    Image(
                        painter = painterResource(id = item.imageId),
                        contentDescription = "employer",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(
                                borderWidth, color = Color.Black,
                                CircleShape
                            )
                    )
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(text = item.companyName, fontSize = 25.sp)
                        Text(text = item.title, fontSize = 15.sp)
                    }

                }
            }



            Row(modifier = Modifier.padding(start = 10.dp,end=10.dp,bottom=10.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width((114.dp))
                        .height(36.dp),

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    shape = RoundedCornerShape(20.dp),


                ) {
                    Text(text = "Принять", color = Color.White, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width((114.dp))
                                        .height(36.dp)   ,

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    shape = RoundedCornerShape(20.dp),


                ) {
                    Text(text = "Отклонить", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}
