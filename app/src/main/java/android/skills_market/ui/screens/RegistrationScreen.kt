package android.skills_market.ui.screens

import android.app.Activity
import android.content.Intent
import android.skills_market.R
import android.skills_market.activities.AppActivity
import android.skills_market.ui.navigation.RegGraph
import android.skills_market.ui.navigation.Screen
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.RegViewModel
import android.skills_market.view_model.event.RegistrationEvent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    onEvent: (RegistrationEvent) -> Unit,
    state: RegUIState.Success
) {
    val regNavController = rememberNavController()
    /*TODO: Доделать переходы и валидацию*/
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Регистрация") },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack(
                                    route = "log_reg_screen",
                                    inclusive = false
                                )
                            },
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back Icon",
                        )
                        Text(
                            text = stringResource(R.string.back),
                        )
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        },
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            RegGraph(
                navController = regNavController,
                onEvent = onEvent,
                state = state
            )
        }
    }
}