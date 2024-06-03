package android.skills_market.ui.screens.profile

import android.skills_market.data.constants.Courses
import android.skills_market.ui.screens.custom_composables.CustomExposedDropdownMenuBox
import android.skills_market.ui.screens.custom_composables.CustomTextField
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
import android.skills_market.ui.text_transformation.PhoneTransformation
import android.skills_market.view_model.ProfileUIState
import android.skills_market.view_model.event.ProfileEvent
import android.skills_market.view_model.event.RegistrationEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Телефонный номер",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 16.sp
                )
                RegistrationTextField(
                    value = state.student.phoneNumber,
                    onValueChange = {
                        if (it.length <= 11) {
                            onEvent(ProfileEvent.SetPhoneNumber(it))
                        }
                    },
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
            }
            Spacer(modifier = Modifier.padding(4.dp))
            CustomTextField(
                heading = "Университет",
                value = state.student.university,
                onValueChange = {
                    onEvent(ProfileEvent.SetUniversity(it))
                },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                heading = "Институт",
                value = state.student.institute,
                onValueChange = {
                    onEvent(ProfileEvent.SetInstitute(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                heading = "Направление",
                value = state.student.direction,
                onValueChange = {
                    onEvent(ProfileEvent.SetDirection(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Курс",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 16.sp
                )
                CustomExposedDropdownMenuBox(
                    initialValue = state.student.course,
                    listOfOptions = listOf(
                        Courses.bachelors1,
                        Courses.bachelors2,
                        Courses.bachelors3,
                        Courses.bachelors4,
                        Courses.masters1,
                        Courses.masters2,
                    ),
                    onOptionChoice = {
                        onEvent(ProfileEvent.SetCourse(it))
                    },
                    placeholderText = "Курс"
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                heading = "Обо мне",
                value = state.student.aboutMe,
                onValueChange = {
                    onEvent(ProfileEvent.SetAboutMe(it))
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                    onEvent(ProfileEvent.UpdateUserInfo)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Сохранить")
            }
        }
    }
}

