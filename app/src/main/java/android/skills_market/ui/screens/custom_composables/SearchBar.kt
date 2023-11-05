package android.skills_market.ui.screens.custom_composables

import android.skills_market.R
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray60
import android.skills_market.ui.theme.Gray70
import android.skills_market.ui.theme.Gray90
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .padding(vertical = 8.dp),
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = stringResource(id = R.string.search),
                    tint = Gray60
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = Gray70
            )
        },
        shape = shapes.extraLarge,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Black,
            focusedTextColor = Black,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Gray90,
            unfocusedContainerColor = Gray90
        ),
    )
}
