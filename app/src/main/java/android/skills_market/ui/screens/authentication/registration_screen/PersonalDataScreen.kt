package android.skills_market.ui.screens.authentication.registration_screen

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.CustomExposedDropdownMenuBox
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
import android.skills_market.ui.text_transformation.DateTransformation
import android.skills_market.ui.text_transformation.PhoneTransformation
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.event.RegistrationEvent
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Date
import java.util.Locale


@Composable
fun PersonalDataScreen(
    navController: NavController,
    onEvent: (RegistrationEvent) -> Unit,
    uiState: RegUIState.Success//TODO убрать Success
) {
    val localContext = LocalContext.current
    var isNameWrong by remember {
        mutableStateOf(false)
    }
    var isSurnameWrong by remember {
        mutableStateOf(false)
    }
    var isPatronymicWrong by remember {
        mutableStateOf(false)
    }
    var isGenderWrong by remember {
        mutableStateOf(false)
    }
    var isBirthDateWrong by remember {
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
                    isSurnameWrong = false
                    onEvent(RegistrationEvent.SetSurname(it))
                },
                label = stringResource(R.string.surname),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                ),
                isError = isSurnameWrong
            )
            RegistrationTextField(
                value = uiState.firstName,
                onValueChange = {
                    isNameWrong = false
                    onEvent(RegistrationEvent.SetName(it))
                },
                label = stringResource(R.string.name),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                ),
                isError = isNameWrong
            )
            RegistrationTextField(
                value = uiState.patronymicName,
                onValueChange = {
                    isPatronymicWrong = false
                    onEvent(RegistrationEvent.SetPatronymic(it))
                },
                label = stringResource(R.string.patronymic),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                ),
                isError = isPatronymicWrong
            )
            CustomExposedDropdownMenuBox(
                listOfOptions = listOf("Мужской", "Женский"),
                onOptionChoice = {
                    isGenderWrong = false
                    onEvent(RegistrationEvent.SetGender(it))
                },
                isError = isGenderWrong
            )
            RegistrationTextField(
                value = uiState.birthDate,
                onValueChange = {
                    isBirthDateWrong = false
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
                },
                isError = isBirthDateWrong
            )
            RegistrationTextField(
                value = uiState.phoneNumber,
                onValueChange = {
                    onEvent(RegistrationEvent.SetPhoneNumber(it))
                },
                label = "Номер телефона (не обязательно)",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = PhoneTransformation(),
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
                        if (
                            uiState.firstName.isNotBlank() &&
                            uiState.secondName.isNotBlank() &&
                            uiState.patronymicName.isNotBlank() &&
                            uiState.gender.isNotBlank() &&
                            uiState.birthDate.isNotBlank()&&
                            uiState.birthDate.length == 8
                        ) {
                            navController.navigate(Screen.UniversityInfoScreen.route)
                        }
                        if (uiState.firstName.isBlank()) {
                            isNameWrong = true
                        }
                        if (uiState.secondName.isBlank()) {
                            isSurnameWrong = true
                        }
                        if (uiState.patronymicName.isBlank()) {
                            isPatronymicWrong = true
                        }
                        if (uiState.gender.isBlank()) {
                            isGenderWrong = true
                        }
                        if (uiState.birthDate.isBlank()) {
                            isBirthDateWrong = true
                        }
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
                    if (
                        uiState.firstName.isNotBlank() &&
                        uiState.secondName.isNotBlank() &&
                        uiState.patronymicName.isNotBlank() &&
                        uiState.gender.isNotBlank() &&
                        uiState.birthDate.isNotBlank()
                    ) {
                        navController.navigate(Screen.UniversityInfoScreen.route)
                    }
                    if (uiState.firstName.isBlank()) {
                        isNameWrong = true
                    }
                    if (uiState.secondName.isBlank()) {
                        isSurnameWrong = true
                    }
                    if (uiState.patronymicName.isBlank()) {
                        isPatronymicWrong = true
                    }
                    if (uiState.gender.isBlank()) {
                        isGenderWrong = true
                    }
                    if (uiState.birthDate.isBlank()) {
                        isBirthDateWrong = true
                    }
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