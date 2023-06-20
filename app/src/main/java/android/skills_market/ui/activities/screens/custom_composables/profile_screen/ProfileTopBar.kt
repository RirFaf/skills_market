package android.skills_market.custom_composables

import android.content.Context
import android.skills_market.R
import android.skills_market.database.SMFirebase
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileTopBar(localContext: Context) {
    val database = SMFirebase()
    var expandDropDownMenu by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "bell",
            modifier = Modifier
                .size(40.dp)
                .clickable {}
        )
        Text(text = "Профиль", fontSize = 28.sp)
        Box(modifier = Modifier) {
            IconButton(
                onClick = { expandDropDownMenu = !expandDropDownMenu }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Show menu"
                )
            }
            DropdownMenu(
                expanded = expandDropDownMenu,
                onDismissRequest = { expandDropDownMenu = false }
            ) {
                Text(
                    text = "Выйти",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = {
                            database.logoutUser(
                                localContext = localContext
                            )
                        }),
                    fontSize = 18.sp,
                )
            }
        }
    }
}