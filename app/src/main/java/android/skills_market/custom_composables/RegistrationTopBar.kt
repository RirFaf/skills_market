package android.skills_market.custom_composables

import android.skills_market.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationTopBar() {
    Row(modifier = Modifier) {
        Button(onClick = {/*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = "Back Icon")
            Text(text = "Назад")
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "Регистрация")
    }

}