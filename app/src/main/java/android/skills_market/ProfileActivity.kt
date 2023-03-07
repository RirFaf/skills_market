package android.skills_market

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Cap()
                ListItem(name = "Роман Королёв", number = "+79541024498")
                profButton("Моё Резюме")
                nonClickable("Место Учёбы")
                studPlace(heading = "Университет", content = "Казанский Федеральный Университет")
                studPlace(heading = "Институт", content = "ИВМиИТ")
                studPlace(heading = "Факультет", content = "Прикладная информатика")

            }

        }
    }

// Как сделать так, чтобы card в которую я засунул шапку менялась независимо от контента в нём

    @Composable

    private fun Cap() {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            elevation = 0.dp
        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "bell",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {


                        }
                )
                Text(text = "Профиль", fontSize = 28.sp)
                Image(
                    painter = painterResource(id = R.drawable.tool),
                    contentDescription = "tool",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {

                        }
                )

            }


        }
    }


    @Composable
    private fun ListItem(name: String, number: String) {
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
                        painter = painterResource(id = R.drawable.worker),
                        contentDescription = "worker",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(2.dp)
                            .size(140.dp)
                            .clip(CircleShape)
                    )
                    Column() {
                        Text(text = name, fontSize = 25.sp)
                        Text(text = number, fontSize = 18.sp)

                    }
                }
            }

        }
    }

    @Preview
    @Composable
    private fun profButton(context: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .padding(10.dp)
                .clickable {
                    Log.d("click", "АУЕ")
                },
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = context, color = Color.White, fontSize = 20.sp)
            }


        }


    }

    @Composable
    fun nonClickable(context: String) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.padding(start = 14.dp)) {
            Text(
                text = context,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }

    @Composable
    fun studPlace(heading: String, content: String) {
        Card(
            modifier = Modifier
                .padding(start = 14.dp, top = 14.dp)
                .fillMaxWidth(),
            elevation = 0.dp
        ) {

            Column() {
                Text(text = heading, color = Color.Gray, fontSize = 20.sp)
                Text(text = content, fontSize = 20.sp)

            }


        }
    }

    @Composable
    fun responsesRow() {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {

        }

    }
}























