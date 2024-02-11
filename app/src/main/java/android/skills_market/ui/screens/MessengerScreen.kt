package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.theme.Inter
import android.skills_market.view_model.MessengerViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessengerScreen(navController: NavController) {
    val viewModel = MessengerViewModel()
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column() {
                        Text(
                            text = "Sample Company Name",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Inter,
                        )
                        Text(
                            text = "Vacancy",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Inter,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
        bottomBar = {
            TextField(
                value = viewModel.currentMessage,
                onValueChange = { viewModel.updateEnteredText(it) },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /*TODO отправка сообщения*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Отправить"
                        )
                    }
                },
                shape = RoundedCornerShape(0.dp)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SentMessageBubble("Здравствуйте")
            ReceivedMessageBubble("Добрый день")
            ReceivedMessageBubble("Собеседования проходят каждый четверг в 16.00")
            SentMessageBubble("Хорошо, увидемся там")
            ReceivedMessageBubble("До встречи!")
        }
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
                .wrapContentSize()
                .widthIn(0.dp, 300.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary
            )
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
                .wrapContentSize()
                .widthIn(0.dp, 300.dp),
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


