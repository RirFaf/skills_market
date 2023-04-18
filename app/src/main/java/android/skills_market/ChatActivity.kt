package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.NavigationBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }
}

@Composable
private fun LoadUI() {
    Scaffold(
        topBar = {},
        bottomBar = { NavigationBar()}
    ) { modifier ->
        Column(modifier = Modifier) {

        }

    }
}