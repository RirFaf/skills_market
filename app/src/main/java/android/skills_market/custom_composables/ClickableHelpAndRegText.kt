package android.skills_market.custom_composables

import android.content.Context
import android.content.Intent
import android.skills_market.R
import android.skills_market.StudentRegisterActivity
import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle

@Composable
fun ClickableHelpAndRegText(localContext: Context) {
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.help)),
        onClick = { offset ->
            Log.d("ClickableHelpAndRegText", "$offset -th character is clicked.")
        }
    )
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.registration)),
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


