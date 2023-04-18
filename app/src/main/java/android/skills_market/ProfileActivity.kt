package android.skills_market

import android.os.Bundle
import android.skills_market.custom_composables.NavigationBar
import android.skills_market.custom_composables.ProfileTopBar
import android.skills_market.custom_composables.StudentInfo
import android.skills_market.custom_composables.ProfileScreenResponsesRow
import android.skills_market.custom_composables.ResumeButton
import android.skills_market.custom_composables.StudPlace
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUI()
        }
    }

    @Composable
    fun LoadUI() {
        Scaffold(
            topBar = { ProfileTopBar() },
            bottomBar = { NavigationBar()}
        ) { modifier ->
            Column(modifier = Modifier) {
                StudentInfo(name = "Роман Королёв", number = "+79541024498")
                ResumeButton()
                Row(
                    modifier = Modifier.padding(start = 14.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Место учёбы",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }
                StudPlace(heading = "Университет", content = "Казанский Федеральный Университет")
                StudPlace(heading = "Институт", content = "ИВМиИТ")
                StudPlace(heading = "Факультет", content = "Прикладная информатика")
                ProfileScreenResponsesRow()
            }
        }
    }
}

























