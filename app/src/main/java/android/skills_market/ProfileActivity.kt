package android.skills_market

import android.skills_market.custom_composables.ResponseCard
import android.skills_market.dataclasses.VacancyModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Cap()
                ListItem(name = "Роман Королёв", number = "+79541024498")
                ResumeButton("Моё Резюме")
                NonClickable("Место Учёбы")
                StudPlace(heading = "Университет", content = "Казанский Федеральный Университет")
                StudPlace(heading = "Институт", content = "ИВМиИТ")
                StudPlace(heading = "Факультет", content = "Прикладная информатика")
                ResponsesRow()

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
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "bell",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable{}
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
    }


    @Composable
    private fun ListItem(name: String, number: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.25f)
                .padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 0.dp,
        ) {
            Box() {
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
    }

    @Composable
    private fun ResumeButton(text: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .padding(10.dp)
                .clickable {
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
                Text(text = text, color = Color.White, fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun NonClickable(context: String) {
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
    fun StudPlace(heading: String, content: String) {
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
    fun ResponsesRow() {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(10.dp)
        ) {
            itemsIndexed(
                listOf(
                    VacancyModel(R.drawable.apple, "Разработчик", "Akvelon"),
                    VacancyModel(R.drawable.twitter, "Тестировщик", "Anderson")
                )
            ) { _, item ->
                ResponseCard(item)
            }
        }
    }
}

























