package android.skills_market

import android.content.Context
import android.os.Bundle
import android.skills_market.custom_composables.LoginPasswordAndButton
import android.skills_market.custom_composables.ClickableHelpAndRegText
import android.skills_market.db_functions.SMFirebase
import android.skills_market.ui.theme.AccentBlue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class
LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI(this)
        }
    }

    override fun onStart() {
        super.onStart()
        val database = SMFirebase()
//        database.authentication(this)
    }
}

@Composable
private fun LoadUI(localContext: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 160.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_logo_dev_24),
            contentDescription = stringResource(id = R.string.logo_description),
            tint = AccentBlue
        )
        LoginPasswordAndButton(localContext = localContext)
        ClickableHelpAndRegText(localContext)
    }
}