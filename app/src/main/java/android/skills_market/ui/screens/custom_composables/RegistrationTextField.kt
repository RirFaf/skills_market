package android.skills_market.ui.screens.custom_composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions(),
    label: String,
    lastField: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = if (lastField) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        shape = MaterialTheme.shapes.medium
    )
    Spacer(modifier = Modifier.padding(4.dp))
}
