package android.skills_market.ui.screens.authentication.registration_screen

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.event.RegistrationEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun NameAndGenderScreen(
    navController: NavController,
    onEvent: (RegistrationEvent) -> Unit,
    uiState: RegUIState.Success//TODO убрать Success
) {
    OutlinedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = uiState.surname,
                onValueChange = {
                    onEvent(RegistrationEvent.SetSurname(it))
                },
                keyboardActions = KeyboardActions(),
                label = stringResource(R.string.surname),
                lastField = false
            )
            RegistrationTextField(
                value = uiState.name,
                onValueChange = {
                    onEvent(RegistrationEvent.SetName(it))
                },
                label = stringResource(R.string.name),
                lastField = false
            )
            RegistrationTextField(
                value = uiState.patronymic,
                onValueChange = {
                    onEvent(RegistrationEvent.SetPatronymic(it))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.CityCourseAndPhoneScreen.route)
                    }
                ),
                label = stringResource(R.string.patronymic),
                lastField = true
            )
            Button(
                onClick = { navController.navigate(Screen.CityCourseAndPhoneScreen.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.next),
                )
            }
        }
    }
}