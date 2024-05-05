package android.skills_market.ui.screens.authentication.registration_screen

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.CustomExposedDropdownMenuBox
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
import android.skills_market.ui.text_transformation.DateTransformation
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.event.RegistrationEvent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PersonalDataScreen(
    navController: NavController,
    onEvent: (RegistrationEvent) -> Unit,
    uiState: RegUIState.Success//TODO убрать Success
) {
    var isPhoneSendable by remember {
        mutableStateOf(false)
    }
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
                value = uiState.secondName,
                onValueChange = {
                    onEvent(RegistrationEvent.SetSurname(it))
                },
                label = stringResource(R.string.surname),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.firstName,
                onValueChange = {
                    onEvent(RegistrationEvent.SetName(it))
                },
                label = stringResource(R.string.name),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.patronymicName,
                onValueChange = {
                    onEvent(RegistrationEvent.SetPatronymic(it))
                },
                label = stringResource(R.string.patronymic),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            CustomExposedDropdownMenuBox(
                listOfOptions = listOf("Мужской", "Женский"),
                onOptionChoice = {
                    onEvent(RegistrationEvent.SetGender(it))
                }
            )
            RegistrationTextField(
                value = uiState.birthDate,
                onValueChange = {
                    onEvent(RegistrationEvent.SetBirthDate(it))
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                label = "Дата рождения",
                visualTransformation = DateTransformation(),
                placeholder = {
                    Text(text = "__.__.____")
                }
            )
            RegistrationTextField(
                value = uiState.phoneNumber,
                onValueChange = {
                    if (it.length <= 11) {
                        onEvent(RegistrationEvent.SetPhoneNumber(it))
                    }
                    if (it.length == 11 || it.isEmpty()) {
                        isPhoneSendable = true
                    } else {
                        isPhoneSendable = false
                    }
                },
                label = "Номер телефона (не обязательно)",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {
                    Text(text = "+_(___)___-__-__")
                }
            )
            RegistrationTextField(
                value = uiState.aboutMe,
                onValueChange = {
                    onEvent(RegistrationEvent.SetAboutMe(it))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.CityCourseAndPhoneScreen.route)
                    }
                ),
                label = "Обо мне (не обязательно)",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                ),
            )
            Button(
                onClick = {
                    navController.navigate(Screen.CityCourseAndPhoneScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.next),
                )
            }
        }
    }
}