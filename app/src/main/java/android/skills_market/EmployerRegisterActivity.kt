package android.skills_market

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class EmployerRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable

private fun LoadUI() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Регистрация"
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("")
            }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("")
            }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("")
            }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("")
            }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("")
            }
        )
        Button(
            onClick = { /*TODO: Проверка наличия логина в бд и валидация имён*/ }
        ) {
            Text(text = "Готово")
        }

    }
}