package android.skills_market.ui.screens

import android.content.Context
import android.skills_market.R
import android.skills_market.custom_composables.ResponseCard
import android.skills_market.network.models.ResponseModel
import android.skills_market.network.SMFirebase
import android.skills_market.ui.screens.custom_composables.LargeButton
import android.skills_market.ui.theme.AccentBlue
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
Родительский класс не имеет задаёт padding всем элементам,
т.к. LazyRow с откликами должна занимать всю ширину экрана
 */
@Composable
fun ProfileScreen(navController: NavController) {
    val localContext = LocalContext.current
    Scaffold(
        topBar = { TopBar(localContext = localContext) },
        modifier = Modifier
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StudentInfo(
                    name = "Роман Королёв",
                    number = "+79541024498",
                    profilePicRes = R.drawable.person_outline
                )
                LargeButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(60.dp),
                    text = stringResource(R.string.my_resume),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Место учёбы",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }
                StudPlace(heading = "Университет", content = "Казанский Федеральный Университет")
                StudPlace(heading = "Институт", content = "ИВМиИТ")
                StudPlace(heading = "Направление", content = "Прикладная информатика")
                ResponsesRow()
            }
        }
    }
}

@Composable
fun StudPlace(heading: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(text = heading, color = Color.Gray, fontSize = 14.sp)
        Text(text = content, fontSize = 20.sp)
    }
}

@Composable
private fun StudentInfo(name: String, number: String, @DrawableRes profilePicRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        val borderWidth = 2.dp
        Image(
            painter = painterResource(id = profilePicRes),
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

@Composable
private fun ResponsesRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(
            listOf(
                ResponseModel(R.drawable.apple_logo, "Разработчик", "Akvelon"),
                ResponseModel(R.drawable.twitter_logo, "Тестировщик", "Anderson")
            )
        ) { _, item ->
            ResponseCard(item)
        }
    }
}

@Composable
private fun TopBar(localContext: Context) {
    val database = SMFirebase()
    var expandDropDownMenu by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {},
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notifications_none),
                contentDescription = "Show menu",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = "Профиль", fontSize = 28.sp)
        IconButton(
            onClick = { expandDropDownMenu = !expandDropDownMenu }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Show menu",
                modifier = Modifier.size(30.dp)
            )
            DropdownMenu(
                expanded = expandDropDownMenu,
                onDismissRequest = { expandDropDownMenu = false }
            ) {
                Text(
                    text = "Выйти",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = {
                            database.logoutUser(
                                localContext = localContext
                            )
                        }),
                    fontSize = 18.sp,
                )
            }
        }

    }
}