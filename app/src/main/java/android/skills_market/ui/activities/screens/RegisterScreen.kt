package android.skills_market.ui.activities.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.ui.activities.screens.custom_composables.common.LogRegTopBar
import android.skills_market.database.SMFirebase
import android.skills_market.data.StudentModel
import android.skills_market.ui.activities.AppActivity
import android.skills_market.ui.activities.screens.custom_composables.common.LargeButton
import android.skills_market.ui.theme.Black
import android.skills_market.view_models.RegViewModel
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

@Composable
fun RegistrationScreen(navController: NavController) {
    val localContext = LocalContext.current
    val database = SMFirebase()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        topBar = {
            LogRegTopBar(
                R.string.registration,
                navController = navController
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 30.dp)
                    .background(Black),
                shape = RoundedCornerShape(26.dp)
            ) {
                Column(
                    modifier = Modifier
//                        .fillMaxSize()
                        .padding(26.dp)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    /*TODO: Доделать переходы и валидацию*/

                    LargeButton(
                        textResource = R.string.done,
                        onClick = {
//                            database.addUser(
////                                StudentModel(
////                                    surname, name, patronymic, city, course, email, password, phone
////                                ),
//                                onSuccessAction = {
//                                    (localContext as Activity).finish()
//                                    localContext.startActivity(
//                                        Intent(
//                                            localContext,
//                                            AppActivity::class.java
//                                        )
//                                    )
//                                }
//                            )
                        },
                        height = 56
                    )
                }
            }
        }
    }
}

@Composable
private fun NameAndGenderRegScreen(viewModel: RegViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    RegistrationTextField(
        value = uiState.surname,
        onValueChange = {viewModel.updateSurname(it)},
        placeholder = R.string.surname,
        lastField = false
    )
    RegistrationTextField(
        value = uiState.name,
        onValueChange = {viewModel.updateName(it)},
        placeholder = R.string.name,
        lastField = false
    )
    RegistrationTextField(
        value = uiState.patronymic,
        onValueChange = {viewModel.updatePatronymic(it)},
        placeholder = R.string.patronymic,
        lastField = false
    )
}
@Composable
private fun CityCourseAndPhone(viewModel: RegViewModel){
    val uiState by viewModel.uiState.collectAsState()
    RegistrationTextField(
        value = uiState.city,
        onValueChange = {viewModel.updateCity(it)},
        placeholder = R.string.city,
        lastField = false
    )
    RegistrationTextField(
        value = uiState.course.toString(),
        onValueChange = {viewModel.updateCourse(it)},
        placeholder = R.string.course_num,
        lastField = false
    )
    RegistrationTextField(
        value = uiState.phone,
        onValueChange = {viewModel.updatePhone(it)},
        placeholder = R.string.phone_number,
        lastField = false
    )
}
@Composable
private fun EmailAndPassword(viewModel: RegViewModel){
    val uiState by viewModel.uiState.collectAsState()
    RegistrationTextField(
        value = uiState.email,
        onValueChange = {viewModel.updateEmail(it)},
        placeholder = R.string.email,
        lastField = false
    )
    RegistrationTextField(
        value = uiState.password,
        onValueChange = {viewModel.updatePassword(it)},
        placeholder = R.string.password,
        lastField = false
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholder: Int,
    lastField: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Black,
            focusedBorderColor = Black,
            focusedLabelColor = Black,
        ),
        label = { Text(text = stringResource(id = placeholder)) },
        singleLine = true,
        placeholder = {
            Text(text = stringResource(id = placeholder))
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = if (lastField) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                /*TODO: иплементировать регистрацию*/
            }),
        shape = shapes.medium
    )
    Spacer(modifier = Modifier.padding(4.dp))
}






