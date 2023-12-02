package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.ui.theme.Typography
import android.skills_market.view_models.LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    navController: NavController,
) {
    val viewModel = LoginViewModel()
    val localContext = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.login))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
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
                    .fillMaxSize()
                    .padding(bottom = 406.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(26.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.personal_data),
                        style = Typography.headlineMedium
                    )
                    OutlinedTextField(
                        value = viewModel.login,
                        onValueChange = { viewModel.updateLogin(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
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
                        label = { Text(text = stringResource(id = R.string.password)) },
                        singleLine = true,
                        placeholder = { Text(text = stringResource(id = R.string.password)) },
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
                                    onEmptyLoginAction = {
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
                                    contentDescription = stringResource(
                                        id = if (passwordVisible)
                                            R.string.hide_password
                                        else R.string.show_password
                                    ),
                                )
                            }
                        },
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Button(
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.login))
                    }
                }
            }
        }
    }
}