package android.skills_market.ui.activities.screens

import android.skills_market.R
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.White
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LogRegScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.hello),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            color = White,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Text(
            text = stringResource(id = R.string.motto),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            color = White,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, White, RoundedCornerShape(10.dp)),
            shape = shapes.medium,
            colors = ButtonDefaults.buttonColors(White)
        ) {
            Text(
                text = stringResource(R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                color = Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Button(
            onClick = {
                navController.navigate(Screen.RegisterScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, White, RoundedCornerShape(10.dp)),
            shape = shapes.medium,
            colors = ButtonDefaults.buttonColors(Black)
        ) {
            Text(
                text = stringResource(R.string.registration),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                color = White,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}