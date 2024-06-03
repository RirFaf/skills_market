package android.skills_market.ui.screens.custom_composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropdownMenuBox(
    modifier: Modifier = Modifier,
    initialValue:String ="",
    listOfOptions: List<String>,
    onOptionChoice: (String) -> Unit,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    placeholderText: String = "",
    isError:Boolean = false
) {
    val localDensity = LocalDensity.current
    var expanded by remember {
        mutableStateOf(false)
    }
    var width by remember {
        mutableStateOf(0.dp)
    }
    var selectedOption by remember {
        mutableStateOf(initialValue)
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select gender",
                )
            },
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    width = with(localDensity) { coordinates.size.width.toDp() }
                },
            colors = colors,
            placeholder = {
                Text(text = placeholderText)
            },
            shape = MaterialTheme.shapes.medium,
            isError = isError
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .heightIn(max = 186.dp)
                .width(width)
                .border(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Transparent,
                    width = 1.dp
                )
                .clip(MaterialTheme.shapes.medium)
        ) {
            listOfOptions.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        onOptionChoice(it)
                        expanded = false
                        selectedOption = it
                    },
                )
            }
        }
    }
}