package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.ui.activities.screens.custom_composables.common.LogRegTopBar
import android.skills_market.ui.activities.screens.custom_composables.LargeButton
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Black
import android.skills_market.view_models.RegViewModel
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.*

@Composable
fun RegistrationScreen(navController: NavHostController) {
    val viewModel = RegViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val regNavController = rememberNavController()
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
            /*TODO: Доделать переходы и валидацию*/
            RegGraph(
                navController = regNavController,
                viewModel = viewModel,
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
        shape = RoundedCornerShape(26.dp)
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
        shape = RoundedCornerShape(26.dp)
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
        shape = RoundedCornerShape(26.dp)
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
                            localContext = localContext,
                            onInvalidCredentialsAction = {},
                            onWeakPasswordAction = {}
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
                        localContext = localContext,
                        onInvalidCredentialsAction = {},
                        onWeakPasswordAction = {}
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
fun RegGraph(
    navController: NavHostController,
    viewModel: RegViewModel
) {
    NavHost(navController = navController, startDestination = Screen.NameAndGenderRegScreen.route) {
        composable(route = Screen.NameAndGenderRegScreen.route) {
            NameAndGenderRegScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.CityCourseAndPhone.route) {
            CityCourseAndPhone(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.EmailAndPasswordScreen.route) {
            EmailAndPasswordScreen(viewModel = viewModel)
        }
    }
}