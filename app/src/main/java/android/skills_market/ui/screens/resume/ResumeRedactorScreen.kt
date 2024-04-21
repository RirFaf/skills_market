package android.skills_market.ui.screens.resume

import android.skills_market.ui.screens.custom_composables.CustomTextField
import android.skills_market.view_model.ResumeUIState
import android.skills_market.view_model.event.ResumeEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeRedactorScreen(
    state: ResumeUIState.Success,//TODO убрать Success
    navController: NavController,
    onEvent: (ResumeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Редактор")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = ""
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
            CustomTextField(
                heading = "Ключевые навыки",
                value = state.keySkills,
                onValueChange = {
                    onEvent(ResumeEvent.SetKeySkills(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                heading = "Курс",
                value = state.course,
                onValueChange = {
                    onEvent(ResumeEvent.SetCourse(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                heading = "Обо мне, дополнительно",
                value = state.about,
                onValueChange = {
                    onEvent(ResumeEvent.SetAbout(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Сохранить")
            }
        }
    }

}