package android.skills_market.ui.screens.messenger

import android.skills_market.R
import android.skills_market.data.network.models.ChatModel
import android.skills_market.data.network.models.MessageModel
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.ui.navigation.Screen
import android.skills_market.view_model.MessengerUIState
import android.skills_market.view_model.event.MessengerEvent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(
    navController: NavController,
    state: MessengerUIState.Success,
    onEvent: (MessengerEvent) -> Unit
) {
    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Чаты")
                },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Show menu",
                        )
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Show menu",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            itemsIndexed(state.chats) { _, chat ->
                ChatCard(
                    onClick = {
                        onEvent(
                            MessengerEvent.GetMessages(
                                chatId = chat.chatId,
                                companyName = chat.companyName,
                                vacancyName = chat.vacancyName,
                                onFailureAction = {}
                            )
                        )
                        navController.navigate(Screen.MessengerScreen.route)
                    },
                    chatModel = chat
                )
            }
        }
    }
}

@Composable
private fun ChatCard(
    onClick: () -> Unit,
    chatModel: ChatModel,
) {
    var chatHeight by remember {
        mutableStateOf(0.dp)
    }
    val localDensity = LocalDensity.current
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .onGloballyPositioned { coordinates ->
                chatHeight = with(localDensity) { coordinates.size.height.toDp() }
            }
            .padding(start = 4.dp),
    ) {
        VerticalDivider(
            modifier = Modifier
                .height(chatHeight - 4.dp)
                .padding(vertical = 2.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 8.dp, top = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = chatModel.companyName,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(Color.Transparent),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = chatModel.messages.last().timestamp.substring(12, 16),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
            }
            Text(
                text = chatModel.vacancyName,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.Transparent),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = chatModel.messages.last().text,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.Transparent),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

