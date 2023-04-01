package android.skills_market.custom_composables

import android.content.Context
import android.skills_market.db_functions.SMFirebase
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginPasswordAndButton(localContext: Context) {
    val database = SMFirebase()
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
    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier.fillMaxWidth(),
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
//                TODO("иплементировать вход через бд")
//                loginUser(localContext = localContext, login,null)
            }),
        trailingIcon = {
            val image = if (passwordVisible) {
                android.skills_market.R.drawable.baseline_visibility_24
            } else {
                android.skills_market.R.drawable.baseline_visibility_off_24
            }

            // Localized description for accessibility services
            val description =
                stringResource(
                    id = if (passwordVisible)
                        android.skills_market.R.string.hide_password
                    else android.skills_market.R.string.show_password
                )

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
            database.loginUser(
                localContext = localContext,
                login,
                password
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
    ) {
        Text(text = stringResource(id = android.skills_market.R.string.login), color = WhiteFontColor)
    }
}
