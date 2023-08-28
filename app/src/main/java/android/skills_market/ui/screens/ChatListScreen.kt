package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray250
import android.skills_market.ui.theme.White
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatListScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            ChatCard(navController = navController)
        }
    }
}

@Composable
private fun ChatCard(navController: NavController) {
    Row(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.MessengerScreen.route) {
                    launchSingleTop = false
                    restoreState = true
                }
            },
    ) {
        Icon(
            modifier = Modifier.padding(top = 2.dp),
            painter = painterResource(id = R.drawable.dev),
            contentDescription = "Chat icon"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Company name",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(Color.Transparent),
                    color = Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "00:00",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    color = Gray250,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
            }
            Text(
                text = "Vacancy",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
            Text(
                text = "Message",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                color = Gray250,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notifications_none),
                contentDescription = "Show menu",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = "Чаты", fontSize = 28.sp)
        IconButton(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Show menu",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
