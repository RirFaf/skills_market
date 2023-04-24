package android.skills_market.navigation.screens

import android.content.Context
import android.skills_market.R
import android.skills_market.custom_composables.RegistrationTextField
import android.skills_market.custom_composables.RegistrationTopBar
import android.skills_market.db_functions.SMFirebase
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import android.skills_market.dataclasses.StudentModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

@Composable
fun RegisterScreen(navController: NavController, localContext: Context) {
    val database = SMFirebase()
    Scaffold(
        topBar = { RegistrationTopBar() }
    ) { modifier ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(3.dp))
            val surname = RegistrationTextField(
                placeholder = R.string.surname,
                lastField = false
            ).lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            val name = RegistrationTextField(
                placeholder = R.string.name,
                lastField = false
            ).lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            val patronymic = RegistrationTextField(
                placeholder = R.string.patronymic,
                lastField = false
            ).lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            val city = RegistrationTextField(
                placeholder = R.string.city,
                lastField = false
            ).lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            val course = RegistrationTextField(placeholder = R.string.course_num, lastField = false)
            val email =
                RegistrationTextField(placeholder = R.string.email, lastField = false).lowercase()
            val password =
                RegistrationTextField(placeholder = R.string.password, lastField = false)
            val phone = RegistrationTextField(placeholder = R.string.phone_number, lastField = true)
                .replace("+7", "8")
            Button(
                onClick = {
                    database.addUser(
                        localContext = localContext,
                        StudentModel(
                            surname, name, patronymic, city, course, email, password, phone
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
            ) {
                Text(text = stringResource(id = R.string.done), color = WhiteFontColor)
            }
        }
    }
}


