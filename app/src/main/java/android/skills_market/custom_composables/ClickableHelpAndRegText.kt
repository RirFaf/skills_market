package android.skills_market.custom_composables

import android.content.Context
import android.content.Intent
import android.skills_market.R
import android.skills_market.StudentRegisterActivity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickableHelpAndRegText(localContext: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.help)),
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            onClick = { offset ->
                Log.d("ClickableHelpAndRegText", "$offset -th character is clicked.")
            }
        )
        Spacer(modifier = Modifier.padding(2.dp))
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.registration)),
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            onClick = {
                localContext.startActivity(
                    Intent(
                        localContext,
                        StudentRegisterActivity::class.java
                    )
                )
            }
        )
    }
}



