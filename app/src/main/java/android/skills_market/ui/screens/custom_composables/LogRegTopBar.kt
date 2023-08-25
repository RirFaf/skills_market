package android.skills_market.ui.activities.screens.custom_composables.common

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Typography
import android.skills_market.ui.theme.White
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview
@Composable
fun LogRegTopBar(
    @StringRes textRes: Int,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 10.dp)
                .clickable { navController.navigate(Screen.LogRegScreen.route) },
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back Icon",
                tint = Color.White
            )

            Text(
                text = stringResource(R.string.back),
                color = White,
                style = Typography.headlineSmall
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(textRes),
                color = White,
                style = Typography.headlineLarge
            )
        }
    }
}