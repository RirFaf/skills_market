package android.skills_market.ui.screens.authentication.registration_screen

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.data.constants.Courses
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.CustomExposedDropdownMenuBox
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UniversityInfoScreen(
    navController: NavController,
    onEvent: (RegistrationEvent) -> Unit,
    uiState: RegUIState.Success//TODO убрать Success
) {
    val localContext = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isCityWrong by remember {
        mutableStateOf(false)
    }
    var isUniversityWrong by remember {
        mutableStateOf(false)
    }
    var isInstituteWrong by remember {
        mutableStateOf(false)
    }
    var isDirectionWrong by remember {
        mutableStateOf(false)
    }
    var isCourseWrong by remember {
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
                value = uiState.city,
                onValueChange = {
                    isCityWrong = false
                    onEvent(RegistrationEvent.SetCity(it))
                },
                label = "Город проживания",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.university,
                onValueChange = {
                    isUniversityWrong = false
                    onEvent(RegistrationEvent.SetUniversity(it))
                },
                label = "Университет",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.institute,
                onValueChange = {
                    isInstituteWrong = false
                    onEvent(RegistrationEvent.SetInstitute(it))
                },
                label = "Институт",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            CustomExposedDropdownMenuBox(
                listOfOptions = listOf(
                    Courses.bachelors1,
                    Courses.bachelors2,
                    Courses.bachelors3,
                    Courses.bachelors4,
                    Courses.masters1,
                    Courses.masters2,
                ),
                onOptionChoice = {
                    isCourseWrong = false
                    onEvent(RegistrationEvent.SetCourse(it))
                },
                isError = isCourseWrong,
                placeholderText = "Курс"
            )
            RegistrationTextField(
                value = uiState.direction,
                onValueChange = {
                    isDirectionWrong = false
                    onEvent(RegistrationEvent.SetDirection(it))
                },
                label = "Направление",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    if (
                        uiState.city.isNotBlank() &&
                        uiState.university.isNotBlank() &&
                        uiState.institute.isNotBlank() &&
                        uiState.direction.isNotBlank() &&
                        uiState.course.isNotBlank()
                    ) {
                        navController.navigate(Screen.EmailAndPasswordScreen.route)
                    }
                    if (uiState.city.isBlank()) {
                        isCityWrong = true
                    }
                    if (uiState.university.isBlank()) {
                        isUniversityWrong = true
                    }
                    if (uiState.institute.isBlank()) {
                        isInstituteWrong = true
                    }
                    if (uiState.direction.isBlank()) {
                        isDirectionWrong = true
                    }
                    if (uiState.course.isBlank()) {
                        isCourseWrong = true
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