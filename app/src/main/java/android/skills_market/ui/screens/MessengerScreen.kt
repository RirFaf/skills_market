package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray150
import android.skills_market.ui.theme.Gray250
import android.skills_market.ui.theme.Inter
import android.skills_market.ui.theme.Teal200
import android.skills_market.view_models.MessengerViewModel
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(
                listOf(1, 2)
            ) { _, _ ->
                SentMessageBubble()
                ReceivedMessageBubble()
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
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
                color = Gray250
                )
        }
    }
}

@Composable
private fun BottomBar(
    viewModel: MessengerViewModel
) {
    TextField(
        value = viewModel.currentMessage,
        onValueChange = { viewModel.updateEnteredText(it) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = "Отправить"
                )
            }
        }
    )
}

@Composable
private fun SentMessageBubble() {
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
                    text = "Да, полностью согласен",
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
private fun ReceivedMessageBubble() {
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
            colors = CardDefaults.cardColors(containerColor = Gray150)
        ) {
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Да, полностью согласен",
                    modifier = Modifier.padding(2.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}


