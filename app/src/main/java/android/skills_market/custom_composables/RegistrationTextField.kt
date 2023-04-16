package android.skills_market.custom_composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationTextField(@StringRes placeholder: Int, lastField: Boolean): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
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
    )
    Spacer(modifier = Modifier.padding(4.dp))
    return text
}