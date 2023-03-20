package android.skills_market

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.skills_market.db_functions.isEmailValid
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }
}

@Composable
private fun LoadUI() {
    var openDialog by remember { mutableStateOf(false) }
    val localContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 160.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_logo_dev_24),
            contentDescription = stringResource(id = R.string.logo_description),
            tint = AccentBlue
        )
        LoginPasswordAndButton(localContext = localContext)
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.help)),
//            style = ,
            onClick = { offset ->
                Log.d("ClickableText", "$offset -th character is clicked.")
            }
        )
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.registration)),
            onClick = {
                openDialog = !openDialog
            }
        )
        LoadPopUp(openDialog = openDialog, localContext)
    }
}

@Composable
private fun LoadPopUp(openDialog: Boolean, localContext: Context) {
    Box {
        val popupWidth = 300.dp
        val popupHeight = 100.dp

        if (openDialog) {

            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties()
            ) {
                Box(
                    modifier = Modifier
                        .size(popupWidth, popupHeight)
                        .padding(top = 6.dp)
                        .background(
                            color = Color.Gray,
                            RoundedCornerShape(10.dp)
                        )
                        .border(2.dp, color = Color.Black, RoundedCornerShape(10.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        StudentRegisterActivity::class.java
                                    )
                                )
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
                        ) {
                            Text(
                                text = stringResource(id = R.string.student),
                                color = WhiteFontColor
                            )
                        }

                        Button(
                            onClick = {
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        EmployerRegisterActivity::class.java
                                    )
                                )
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
                        ) {
                            Text(
                                text = stringResource(id = R.string.employer),
                                color = WhiteFontColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginPasswordAndButton(localContext: Context) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // Creating a variable to store toggle state
    var passwordVisible by remember { mutableStateOf(false) }
    // Create a Text Field for giving password input
    TextField(
        value = login,
        onValueChange = { login = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.login)) },
        singleLine = true,
        placeholder = {
            Text(stringResource(id = R.string.login))
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = ImeAction.Next
        ),
    )
    Spacer(modifier = Modifier.padding(4.dp))
    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier.fillMaxWidth(),
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
                keyboardController?.hide()
                /*TODO: иплементировать вход через бд*/
                loginUser(localContext = localContext, login)
            }),
        trailingIcon = {
            val image = if (passwordVisible) {
                R.drawable.baseline_visibility_24
            } else {
                R.drawable.baseline_visibility_off_24
            }

            // Localized description for accessibility services
            val description =
                stringResource(id = if (passwordVisible) R.string.hide_password else R.string.show_password)

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = painterResource(id = image), description, tint = AccentBlue)
            }
        }
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Button(
        onClick = {
            /*TODO: иплементировать вход через бд*/
            loginUser(localContext = localContext, login)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
    ) {
        Text(text = stringResource(id = R.string.login), color = WhiteFontColor)
    }
}

private fun loginUser(localContext: Context, login: String) {
    if (isEmailValid(login)) {
        localContext.startActivity(
            Intent(
                localContext,
                SearchActivity::class.java
            )
        )
    }
}
