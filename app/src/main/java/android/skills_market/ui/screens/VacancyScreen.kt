package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.ui.screens.custom_composables.CustomText
import android.skills_market.ui.theme.Inter
import android.skills_market.view_model.VacancyUIState
import android.skills_market.view_model.event.VacancyEvent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyScreen(
    navController: NavController,
    onEvent: (VacancyEvent) -> Unit,
    state: VacancyUIState.Success
) {
    val localContext = LocalContext.current
    val vacancy = state.vacancy
    var liked by remember {
        mutableStateOf(false)
    }
    liked = vacancy.liked
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Вакансия") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            liked = !liked
                            onEvent(VacancyEvent.ChangeLiked(vacancyId = vacancy.id))
                        }
                    ) {
                        Icon(
                            imageVector = if (liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "ChangeLiked",
                        )
                    }
                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                Modifier
                    .height(90.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = vacancy.position,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Inter,
                )
            }
            CustomText(heading = "Отрасль", content = vacancy.edArea)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Компания", content = vacancy.company.name)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Тип занятости", content = vacancy.formOfEmployment)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Требования", content = vacancy.requirements)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Расположение", content = vacancy.location)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "Заработная плата", content = vacancy.salary.toString())
            Spacer(modifier = Modifier.padding(8.dp))
            CustomText(heading = "О вакансии", content = vacancy.about)
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(
                        localContext,
                        "Work in progress vacancy id:${vacancy.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                    onEvent(VacancyEvent.Respond(vacancyId = vacancy.id.toInt()))
                }
            ) {
                Text(text = "Откликнуться")
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}