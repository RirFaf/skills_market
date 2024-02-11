package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.LogRegActivity
import android.skills_market.network.SMFirebase
import android.skills_market.ui.screens.custom_composables.CustomText
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ProfileScreen(navController: NavController) {
    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Профиль") },
                actions = {
                    val database = SMFirebase()
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
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "worker",
                )
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
                    navController.navigate("resume_redactor_screen")
                },
            ) {
                Text(
                    text = stringResource(R.string.my_resume),
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Место учёбы",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            CustomText(heading = "Университет", content = "Казанский Федеральный Университет")
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Институт", content = "ИВМиИТ")
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Направление", content = "Прикладная информатика")
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

