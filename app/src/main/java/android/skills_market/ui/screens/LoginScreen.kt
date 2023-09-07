package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.ui.activities.AppActivity
import android.skills_market.ui.screens.custom_composables.LargeButton
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import android.skills_market.view_models.LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
) {
    val viewModel = LoginViewModel()
    val localContext = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar(navController = navController) },
        containerColor = Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 436.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = White)
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
                        text = stringResource(id = R.string.personal_data),
                        color = Black,
                        style = Typography.headlineMedium
                    )
                    OutlinedTextField(
                        value = viewModel.login,
                        onValueChange = { viewModel.updateLogin(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            errorBorderColor = Color.Red,
                            errorLabelColor = Color.Red,
                            cursorColor = Black,
                            focusedBorderColor = Black,
                            focusedLabelColor = Black,
                        ),
                        isError = uiState.isLoginCorrect,
                        label = { Text(text = stringResource(id = android.skills_market.R.string.login)) },
                        singleLine = true,
                        placeholder = {
                            Text(stringResource(id = android.skills_market.R.string.login))
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            imeAction = ImeAction.Next
                        ),
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.updatePassword(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            errorBorderColor = Color.Red,
                            errorLabelColor = Color.Red,
                            cursorColor = Black,
                            focusedBorderColor = Black,
                            focusedLabelColor = Black,
                        ),
                        isError = uiState.isPasswordCorrect,
                        label = { Text(text = stringResource(id = R.string.password)) },
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
                                /*TODO: сделать нормальную валидацию*/
                                viewModel.loginUser(
                                    onSuccessAction = {
                                        (localContext as Activity).finish()
                                        localContext.startActivity(
                                            Intent(
                                                localContext,
                                                AppActivity::class.java
                                            )
                                        )
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
                                            "Введите логин",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                                keyboardController?.hide()
                            }
                        ),
                        trailingIcon = {
                            val image = if (passwordVisible) {
                                R.drawable.visibility
                            } else {
                                R.drawable.visibility_off
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
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    LargeButton(
                        text = stringResource(id = R.string.login),
                        onClick = {
                            /*TODO: сделать нормальную валидацию*/
                            viewModel.loginUser(
                                onSuccessAction = {
                                    (localContext as Activity).finish()
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            AppActivity::class.java
                                        )
                                    )
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
                                        "Введите логин",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                            keyboardController?.hide()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Black)
                    )
                }
            }
        }
    }
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
            androidx.compose.material.Icon(
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
                text = stringResource(R.string.login),
                color = White,
                style = Typography.headlineLarge
            )
        }
    }
}