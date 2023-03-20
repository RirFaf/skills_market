package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.RegistrationTextField
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class StudentRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }
}

@Composable
private fun LoadUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.registration)
        )
        RegistrationTextField(placeholder = R.string.surname, lastField = false)
        RegistrationTextField(placeholder = R.string.name, lastField = false)
        RegistrationTextField(placeholder = R.string.patronymic, lastField = false)
        RegistrationTextField(placeholder = R.string.city, lastField = false)
        RegistrationTextField(placeholder = R.string.course_num, lastField = false)
        RegistrationTextField(placeholder = R.string.email, lastField = false)
        RegistrationTextField(placeholder = R.string.phone_number, lastField = true)
        Button(
            onClick = { /*TODO: Проверка наличия логина в бд и валидация имён*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
        ) {
            Text(text = stringResource(id = R.string.done), color = WhiteFontColor)
        }
    }
}
