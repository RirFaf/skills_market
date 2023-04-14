package android.skills_market.custom_composables

import android.icu.text.CaseMap.Title
import android.skills_market.dataclasses.Resume
import android.skills_market.dataclasses.Vacancy
import android.skills_market.ui.theme.CardBackgroundGray
import android.skills_market.ui.theme.Teal200
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResumeCard(resume: Resume) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = resume.title,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = resume.description,
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundGray)
        )
        Spacer(modifier = Modifier.padding(2.dp))
    }
}