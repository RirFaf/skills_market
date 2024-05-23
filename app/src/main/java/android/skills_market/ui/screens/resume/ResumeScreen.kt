package android.skills_market.ui.screens.resume

import android.skills_market.data.constants.Courses
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.CustomText
import android.skills_market.view_model.ResumeUIState
import android.skills_market.view_model.event.ResumeEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeScreen(
    state: ResumeUIState.Success,//TODO убрать Success
    navController: NavController,
    onEvent: (ResumeEvent) -> Unit
) {
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.ResumeRedactorScreen.route) },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit resume")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp, vertical = 40.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Иванов Иван", fontSize = 25.sp)
                Text(text = "+79123456789", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Резюме",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            CustomText(heading = "Ключевые навыки", content = state.keySkills)
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Желаемая специализация", content = state.course)
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(heading = "Обо мне, дополнительно", content = state.about)
        }
    }
}