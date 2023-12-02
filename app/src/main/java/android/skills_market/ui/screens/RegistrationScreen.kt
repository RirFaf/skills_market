package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.ui.navigation.RegGraph
import android.skills_market.ui.navigation.Screen
import android.skills_market.view_models.RegViewModel
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController) {
    val viewModel = RegViewModel()
    val regNavController = rememberNavController()
    /*TODO: Доделать переходы и валидацию*/
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Регистрация") },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
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
                        )
                        Text(
                            text = stringResource(R.string.back),
                        )
                    }
                }
            )
        },
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
                value = viewModel.surname,
                onValueChange = { viewModel.updateSurname(it) },
                keyboardActions = KeyboardActions(),
                placeholder = R.string.surname,
                lastField = false
            )
            RegistrationTextField(
                value = viewModel.name,
                onValueChange = { viewModel.updateName(it) },
                placeholder = R.string.name,
                lastField = false
            )
            RegistrationTextField(
                value = viewModel.patronymic,
                onValueChange = { viewModel.updatePatronymic(it) },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.CityCourseAndPhone.route)
                    }
                ),
                placeholder = R.string.patronymic,
                lastField = true
            )
            Button(
                onClick = { navController.navigate(Screen.CityCourseAndPhone.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.next),
                )
            }
        }
    }
}

@Composable
fun CityCourseAndPhone(viewModel: RegViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
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
                value = viewModel.city,
                onValueChange = { viewModel.updateCity(it) },
                placeholder = R.string.city,
                lastField = false
            )
            RegistrationTextField(
                value = viewModel.course,
                onValueChange = { viewModel.updateCourse(it) },
                placeholder = R.string.course_num,
                lastField = false
            )
            RegistrationTextField(
                value = viewModel.phone,
                onValueChange = { viewModel.updatePhone(it) },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate(Screen.EmailAndPasswordScreen.route)
                    }
                ),
                placeholder = R.string.phone_number,
                lastField = true
            )
            Button(
                onClick = { navController.navigate(Screen.EmailAndPasswordScreen.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.next),
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailAndPasswordScreen(viewModel: RegViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState by viewModel.uiState.collectAsState()
    val localContext = LocalContext.current
    OutlinedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = viewModel.email,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = R.string.email,
                lastField = false
            )
            RegistrationTextField(
                value = viewModel.password,
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
                            onEmptyLoginAction = {
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
            Button(
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
                        onEmptyLoginAction = {
                            Toast.makeText(
                                localContext,
                                "Введите почту",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.done),
                )
            }
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
        label = { Text(text = stringResource(id = placeholder)) },
        singleLine = true,
//        placeholder = {
//            Text(text = stringResource(id = placeholder))
//        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = if (lastField) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        shape = shapes.medium
    )
    Spacer(modifier = Modifier.padding(4.dp))
}



