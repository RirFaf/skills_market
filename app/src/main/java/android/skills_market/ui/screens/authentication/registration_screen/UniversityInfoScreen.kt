package android.skills_market.ui.screens.authentication.registration_screen

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.ui.screens.custom_composables.RegistrationTextField
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.event.RegistrationEvent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UniversityInfoScreen(
    navController: NavController,
    onEvent: (RegistrationEvent) -> Unit,
    uiState: RegUIState.Success//TODO убрать Success
) {
    val localContext = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 30.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegistrationTextField(
                value = uiState.city,
                onValueChange = {
                    onEvent(RegistrationEvent.SetCity(it))
                },
                label = "Город проживания",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.university,
                onValueChange = {
                    onEvent(RegistrationEvent.SetUniversity(it))
                },
                label = "Университет",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.institute,
                onValueChange = {
                    onEvent(RegistrationEvent.SetInstitute(it))
                },
                label = "Институт",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                )
            )
            RegistrationTextField(
                value = uiState.direction,
                onValueChange = {
                    onEvent(RegistrationEvent.SetDirection(it))
                },
                label = "Направление",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.done),
                )
            }
        }
    }
}


//keyboardActions = KeyboardActions(
//onDone = {
//    onEvent(
//        RegistrationEvent.AddUser(
//            onSuccessAction = {
//                (localContext as Activity).finish()
//                localContext.startActivity(
//                    Intent(
//                        localContext,
//                        AppActivity::class.java
//                    )
//                )
//            },
//            onFailureAction = {
//                Toast.makeText(
//                    localContext,
//                    "Попробуйте ещё раз",
//                    Toast.LENGTH_SHORT
//                ).show()
//            },
//            onEmptyPasswordAction = {
//                Toast.makeText(
//                    localContext,
//                    "Введите пароль",
//                    Toast.LENGTH_SHORT
//                ).show()
//            },
//            onEmptyLoginAction = {
//                Toast.makeText(
//                    localContext,
//                    "Введите почту",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        )
//    )
//    keyboardController?.hide()
//}
//),