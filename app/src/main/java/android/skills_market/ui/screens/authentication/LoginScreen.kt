package android.skills_market.ui.screens.authentication

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.ui.theme.Typography
import android.skills_market.view_model.LoginUIState
import android.skills_market.view_model.event.LoginEvent
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
    state: LoginUIState.Success,//TODO убрать Success
    navController: NavController,
    onEvent: (LoginEvent) -> Unit
) {
    val localContext = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoginWrong by remember {
        mutableStateOf(false)
    }
    var isPasswordWrong by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Вход") },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
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
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
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
                    Text(
                        text = stringResource(id = R.string.personal_data),
                        style = Typography.headlineMedium
                    )
                    OutlinedTextField(
                        value = state.email,
                        onValueChange = {
                            isLoginWrong = false
                            onEvent(LoginEvent.SetLogin(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text(text = stringResource(id = R.string.login)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email
                        ),
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    OutlinedTextField(
                        value = state.password,
                        onValueChange = {
                            isPasswordWrong = false
                            onEvent(LoginEvent.SetPassword(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text(text = stringResource(id = R.string.password)) },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onEvent(LoginEvent.LoginUser(
                                    onSuccessAction = {
                                        Log.d("MyTag", "onSuccess called")
                                        (localContext as Activity).finish()
                                        localContext.startActivity(
                                            Intent(
                                                localContext,
                                                AppActivity::class.java
                                            )
                                        )
                                    },
                                    onFailureAction = {
                                        Log.e("MyTag", "onFailure called")
                                    },
                                    onEmptyPasswordAction = {
                                        isPasswordWrong = true
                                    },
                                    onEmptyLoginAction = {
                                        isLoginWrong = true
                                    }
                                ))
                                keyboardController?.hide()
                            }
                        ),
                        trailingIcon = {
                            //Изменеие видимости пароля
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        id =
                                        if (passwordVisible)
                                            R.drawable.visibility
                                        else
                                            R.drawable.visibility_off
                                    ),
                                    contentDescription = "password visibility",
                                )
                            }
                        },
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Button(
                        onClick = {
                            onEvent(LoginEvent.LoginUser(
                                onSuccessAction = {
                                    Log.d("MyTag", "onSuccess called")
                                    (localContext as Activity).finish()
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            AppActivity::class.java
                                        )
                                    )
                                },
                                onFailureAction = {
                                    Log.e("MyTag", "onFailure called")
                                },
                                onEmptyPasswordAction = {
                                    Log.d("MyTag", "onEmptyPassword called")
                                    isPasswordWrong = true
                                },
                                onEmptyLoginAction = {
                                    Log.d("MyTag", "onEmptyLogin called")
                                    isLoginWrong = true
                                }
                            ))
                            keyboardController?.hide()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.login))
                    }
                }
            }
        }
    }
}