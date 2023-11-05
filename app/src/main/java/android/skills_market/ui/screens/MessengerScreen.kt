package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray50
import android.skills_market.ui.theme.Gray70
import android.skills_market.ui.theme.Inter
import android.skills_market.ui.theme.Teal200
import android.skills_market.ui.theme.White
import android.skills_market.view_models.MessengerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MessengerScreen(navController: NavController) {
    val viewModel = MessengerViewModel()
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { TopBar(navController = navController) },
        bottomBar = {
            BottomBar(
                viewModel = viewModel
            )
        },
        containerColor = White
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(
                listOf(1, 2)
            ) { _, _ ->
                SentMessageBubble("Да")
                ReceivedMessageBubble("Нет")
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                modifier = Modifier.size(30.dp)
            )
        }
        Column() {
            Text(
                text = "Company Name",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter,
                color = Black
            )
            Text(
                text = "Vacancy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Inter,
                color = Gray70
            )
        }
    }
}

@Composable
private fun BottomBar(
    viewModel: MessengerViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier.padding(2.dp),
            color = Black,
            thickness = 1.dp
        )
        TextField(
            value = viewModel.currentMessage,
            onValueChange = { viewModel.updateEnteredText(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Отправить"
                    )
                }
            },
            shape = shapes.extraSmall,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                cursorColor = Black
            )
        )
    }
}

@Composable
private fun SentMessageBubble(text: String) {
    Spacer(modifier = Modifier.padding(2.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(containerColor = Teal200)
        ) {
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = text,
                    modifier = Modifier.padding(2.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}

@Composable
private fun ReceivedMessageBubble(text: String) {
    Spacer(modifier = Modifier.padding(2.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(containerColor = Gray50)
        ) {
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = text,
                    modifier = Modifier.padding(2.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}


