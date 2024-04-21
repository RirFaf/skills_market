package android.skills_market.ui.screens.profile

import android.provider.ContactsContract.Profile
import android.skills_market.ui.screens.custom_composables.CustomTextField
import android.skills_market.view_model.ProfileUIState
import android.skills_market.view_model.ResumeViewModel
import android.skills_market.view_model.event.ProfileEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileRedactorScreen(
    navController: NavController,
    state: ProfileUIState.Success,//TODO убрать Success
    onEvent: (ProfileEvent) -> Unit
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
                heading = "Номер телефона",
                value = state.student?.phone ?: "Отсутствует",
                onValueChange = {
                    onEvent(ProfileEvent.SetPhoneNumber(it))
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

