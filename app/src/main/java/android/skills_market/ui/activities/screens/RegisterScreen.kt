package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.ui.activities.screens.custom_composables.common.LogRegTopBar
import android.skills_market.database.SMFirebase
import android.skills_market.data.StudentModel
import android.skills_market.ui.activities.screens.custom_composables.common.LargeButton
import android.skills_market.ui.theme.Black
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
                .background(Black)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 45.dp)
                    .background(Black),
                shape = RoundedCornerShape(26.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(26.dp)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val surname = RegistrationTextField(
                        placeholder = R.string.surname,
                        lastField = false
                    ).lowercase()
                        .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }
                    val name = RegistrationTextField(
                        placeholder = R.string.name,
                        lastField = false
                    ).lowercase()
                        .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }
                    val patronymic = RegistrationTextField(
                        placeholder = R.string.patronymic,
                        lastField = false
                    ).lowercase()
                        .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }
                    val city = RegistrationTextField(
                        placeholder = R.string.city,
                        lastField = false
                    ).lowercase()
                        .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }
                    val course =
                        RegistrationTextField(placeholder = R.string.course_num, lastField = false)
                    val email =
                        RegistrationTextField(
                            placeholder = R.string.email,
                            lastField = false
                        ).lowercase()
                    val password =
                        RegistrationTextField(placeholder = R.string.password, lastField = false)
                    val phone =
                        RegistrationTextField(placeholder = R.string.phone_number, lastField = true)
                            .replace("+7", "8")
                    Spacer(Modifier.height(8.dp))
                    LargeButton(
                        textResource = R.string.done,
                        onClick = {
                            database.addUser(
                                localContext = localContext,
                                StudentModel(
                                    surname, name, patronymic, city, course, email, password, phone
                                )
                            )
                        },
                        height = 56
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationTextField(@StringRes placeholder: Int, lastField: Boolean): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
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
    return text
}




