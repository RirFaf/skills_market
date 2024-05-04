package android.skills_market.ui.screens.profile

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.LogRegActivity
import android.skills_market.data.constants.Courses
import android.skills_market.data.network.SMFirebase
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.CustomText
import android.skills_market.view_model.ProfileUIState
import android.skills_market.view_model.event.ProfileEvent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    state: ProfileUIState.Success,//TODO убрать Success
    onEvent: (ProfileEvent) -> Unit
) {
    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Профиль") },
                actions = {
                    val database = SMFirebase
                    IconButton(
                        onClick = {
                            Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                                .show()
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notifications_none),
                            contentDescription = "Show menu",
                        )
                    }
                    IconButton(
                        onClick = {
                            //TODO засунусть в обработчик событий
                            database.logoutUser(
                                onLogoutAction = {
                                    (localContext as Activity).finish()
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            LogRegActivity::class.java
                                        )
                                    )
                                }
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = "Logout",
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.ProfileRedactorScreen.route) },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit profile")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(//TODO сменить на изображение
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "worker",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Иванов Иван", fontSize = 25.sp)
                    Text(text = "+79123456789", fontSize = 18.sp)
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = {
                    navController.navigate(Screen.ResumeScreen.route)
                },
            ) {
                Text(
                    text = stringResource(R.string.my_resume),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Место учёбы",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            CustomText(heading = "Университет", content = "Казанский Федеральный Университет")
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Институт", content = "ИВМиИТ")
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Направление", content = "Прикладная информатика")
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Код направления", content = "09.03.03")
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Курс", content = Courses.bachelors4)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

