package android.skills_market

import android.os.Bundle
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }
}

@Composable
private fun LoadUI() {
    SearchBar()
}

@Composable
private fun SearchBar() {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text("Поиск")
            }
        )
        Button(
            onClick = { /*TODO: Имплементировать поиск*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Local description",
                tint = WhiteFontColor
            )
        }
    }
}