package android.skills_market.ui.activities.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.db_functions.SMFirebase
import android.skills_market.ui.activities.AppActivity
import android.skills_market.ui.activities.screens.custom_composables.common.LogRegTopBar
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray150
import android.skills_market.ui.theme.Gray900
import android.skills_market.view_models.LoginViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val localContext = LocalContext.current
    val loginUIState by loginViewModel.uiState.collectAsState()
    val database = SMFirebase()
    val keyboardController = LocalSoftwareKeyboardController.current
    // Creating a variable to store toggle state
    var passwordVisible by remember { mutableStateOf(false) }
    val login = loginViewModel.login
    val password = loginViewModel.password

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            LogRegTopBar(
                textRes = R.string.login,
                navController = navController
            )
        },
        backgroundColor = Black
    ) { modifier ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 18.dp, bottom = 450.dp)
                .background(Black),
            shape = RoundedCornerShape(26.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(26.dp)
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Личные данные",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
                OutlinedTextField(
                    value = login,
                    onValueChange = { loginViewModel.updateLogin(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(3.dp, Color.Transparent, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Black,
                        focusedBorderColor = Black,
                        focusedLabelColor = Black,
                    ),
                    label = { Text(text = stringResource(id = android.skills_market.R.string.login)) },
                    singleLine = true,
                    placeholder = {
                        Text(stringResource(id = android.skills_market.R.string.login))
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        imeAction = ImeAction.Next
                    ),
                )
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { loginViewModel.updatePassword(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(3.dp, Color.Transparent, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Black,
                        focusedBorderColor = Black,
                        focusedLabelColor = Black,
                    ),
                    label = { Text(text = stringResource(id = android.skills_market.R.string.password)) },
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = android.skills_market.R.string.password)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            database.loginUser(
                                successfulAction = {
                                    (localContext as Activity).finish()
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            AppActivity::class.java
                                        )
                                    )
                                },
                                login = login,
                                password = password
                            )
                        }),
                    trailingIcon = {
                        val image = if (passwordVisible) {
                            R.drawable.baseline_visibility_24
                        } else {
                            R.drawable.baseline_visibility_off_24
                        }

                        //Изменеие видимости пароля
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = image),
                                stringResource(
                                    id = if (passwordVisible)
                                        R.string.hide_password
                                    else R.string.show_password
                                ),
                                tint = Black
                            )
                        }
                    },
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Button(
                    onClick = {
                        /*TODO: иплементировать вход через бд*/
                        database.loginUser(
                            successfulAction = {
                                (localContext as Activity).finish()
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        AppActivity::class.java
                                    )
                                )
                            },
                            login = login,
                            password = password
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Gray900)
                ) {
                    Text(
                        text = stringResource(id = android.skills_market.R.string.logging_in),
                        color = Gray150
                    )
                }
            }
        }
    }
}

