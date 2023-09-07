package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.ui.activities.AppActivity
import android.skills_market.ui.navigation.RegGraph
import android.skills_market.ui.screens.custom_composables.LargeButton
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import android.skills_market.view_models.RegViewModel
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.util.*

@Composable
fun RegistrationScreen(navController: NavHostController) {
    val viewModel = RegViewModel()
    val regNavController = rememberNavController()
    /*TODO: Доделать переходы и валидацию*/
    Scaffold(
        topBar = { TopBar(navController = navController) },
        containerColor = Black
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            RegGraph(
                navController = regNavController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun NameAndGenderRegScreen(viewModel: RegViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp)
            .background(Black),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = uiState.surname,
                onValueChange = { viewModel.updateSurname(it) },
                keyboardActions = KeyboardActions(),
                placeholder = R.string.surname,
                lastField = false
            )
            RegistrationTextField(
                value = uiState.name,
                onValueChange = { viewModel.updateName(it) },
                placeholder = R.string.name,
                lastField = false
            )
            RegistrationTextField(
                value = uiState.patronymic,
                onValueChange = { viewModel.updatePatronymic(it) },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.CityCourseAndPhone.route)
                    }
                ),
                placeholder = R.string.patronymic,
                lastField = true
            )
            LargeButton(
                text = stringResource(R.string.next),
                onClick = { navController.navigate(Screen.CityCourseAndPhone.route) },
                colors = ButtonDefaults.buttonColors(Black)
            )
        }
    }
}

@Composable
fun CityCourseAndPhone(viewModel: RegViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp)
            .background(Black),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = uiState.city,
                onValueChange = { viewModel.updateCity(it) },
                placeholder = R.string.city,
                lastField = false
            )
            RegistrationTextField(
                value = uiState.course,
                onValueChange = { viewModel.updateCourse(it) },
                placeholder = R.string.course_num,
                lastField = false
            )
            RegistrationTextField(
                value = uiState.phone,
                onValueChange = { viewModel.updatePhone(it) },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.EmailAndPasswordScreen.route)
                    }
                ),
                placeholder = R.string.phone_number,
                lastField = true
            )
            LargeButton(
                text = stringResource(R.string.next),
                onClick = { navController.navigate(Screen.EmailAndPasswordScreen.route) },
                colors = ButtonDefaults.buttonColors(Black)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailAndPasswordScreen(viewModel: RegViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState by viewModel.uiState.collectAsState()
    val localContext = LocalContext.current
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp)
            .background(Black),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = R.string.email,
                lastField = false
            )
            RegistrationTextField(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.register(
                            onSuccessAction = {
                                (localContext as Activity).finish()
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        AppActivity::class.java
                                    )
                                )
                            },
                            onFailureAction = {
                                Toast.makeText(
                                    localContext,
                                    "Упс.. попробуйте ещё раз",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onEmptyPasswordAction = {
                                Toast.makeText(
                                    localContext,
                                    "Введите пароль",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onEmptyLoginAction =  {
                                Toast.makeText(
                                    localContext,
                                    "Введите почту",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                        keyboardController?.hide()
                    }
                ),
                placeholder = R.string.password,
                lastField = true
            )
            LargeButton(
                text = stringResource(R.string.done),
                onClick = {
                    viewModel.register(

                        onSuccessAction = {
                            (localContext as Activity).finish()
                            localContext.startActivity(
                                Intent(
                                    localContext,
                                    AppActivity::class.java
                                )
                            )
                        },
                        onFailureAction = {
                            Toast.makeText(
                                localContext,
                                "Упс.. попробуйте ещё раз",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onEmptyPasswordAction = {
                            Toast.makeText(
                                localContext,
                                "Введите пароль",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onEmptyLoginAction =  {
                            Toast.makeText(
                                localContext,
                                "Введите почту",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(Black)
            )
        }
    }
}

@Composable
fun RegistrationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions(),
    @StringRes placeholder: Int,
    lastField: Boolean
) {
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
        keyboardActions = keyboardActions,
        shape = shapes.medium
    )
    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
private fun TopBar(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 10.dp)
                .clickable {
                    navController.popBackStack(
                        route = "log_reg_screen",
                        inclusive = false
                    )
                },
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back Icon",
                tint = Color.White
            )

            androidx.compose.material.Text(
                text = stringResource(R.string.back),
                color = White,
                style = Typography.headlineSmall
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            androidx.compose.material.Text(
                text = stringResource(R.string.registration),
                color = White,
                style = Typography.headlineLarge
            )
        }
    }
}

