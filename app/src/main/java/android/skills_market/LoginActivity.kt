package android.skills_market

import android.content.Intent
import android.os.Bundle
import android.skills_market.ui.theme.AccentBlue
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Preview(showSystemUi = true)
@Composable
private fun LoadUI() {
    var openDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val localContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_logo_dev_24),
            contentDescription = "Local description",
            tint = AccentBlue
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("Логин")
            }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("Пароль")
            }
        )
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Войти")
        }
        ClickableText(
            text = AnnotatedString("Помощь"),
            onClick = { offset ->
                Log.d("ClickableText", "$offset -th character is clicked.")
            }
        )
        ClickableText(
            text = AnnotatedString("Регистрация"),
            onClick = { openDialog = !openDialog
//                offset ->
//                localContext.startActivity(
//                    Intent(
//                        localContext,
//                        StudentRegisterActivity::class.java
//                    )
//                )
            }
        )
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
                            .background(color = Color.Gray,
                                RoundedCornerShape(20.dp))
                            .border(2.dp, color = Color.Black, RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Welcome",
                                color = Color.White,
                                modifier = Modifier.padding(vertical = 5.dp),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}