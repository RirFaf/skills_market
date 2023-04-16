package android.skills_market.custom_composables

import android.content.Context
import android.content.Intent
import android.skills_market.EmployerRegisterActivity
import android.skills_market.StudentRegisterActivity
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.WhiteFontColor
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun ClickableTextAndPopUp(localContext: Context) {
    var openDialog by remember { mutableStateOf(false) }
    ClickableText(
        text = AnnotatedString(stringResource(id = android.skills_market.R.string.help)),
//            style = ,
        onClick = { offset ->
            Log.d("ClickableText", "$offset -th character is clicked.")
        }
    )
    ClickableText(
        text = AnnotatedString(stringResource(id = android.skills_market.R.string.registration)),
        onClick = {
            openDialog = !openDialog
        }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow),
    ){
        val popupWidth = 300.dp
        val popupHeight = 100.dp

        if (openDialog) {
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties(),
                onDismissRequest = {
                    openDialog = false
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = popupHeight, width = popupWidth)
                        .padding(top = 6.dp)
//                        .background(
//                            color = Color.Gray,
//                            RoundedCornerShape(10.dp)
//                        )
                        .border(2.dp, color = Color.Black, RoundedCornerShape(10.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier.padding(2.dp),
                            onClick = {
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        StudentRegisterActivity::class.java
                                    )
                                )
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
                        ) {
                            Text(
                                text = stringResource(id = android.skills_market.R.string.student),
                                color = WhiteFontColor
                            )
                        }

                        Button(
                            modifier = Modifier.padding(2.dp),
                            onClick = {
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        EmployerRegisterActivity::class.java
                                    )
                                )
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor)
                        ) {
                            Text(
                                text = stringResource(id = android.skills_market.R.string.employer),
                                color = WhiteFontColor
                            )
                        }
                    }
                }
            }
        }
    }
}


