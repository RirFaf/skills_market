package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.network.models.ShortVacancyModel
import android.skills_market.ui.screens.custom_composables.VacancyCard
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, vacancies: ArrayList<ShortVacancyModel>) {
    val localContext = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    var text by remember { mutableStateOf(TextFieldValue("")) }
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(id = R.string.search),
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.search),
                            )
                        },
                        shape = RoundedCornerShape(32.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        singleLine = true
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_alt),
                            contentDescription = "filter",
                        )
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(localContext, "Work in progress", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sort),
                            contentDescription = "sort",
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(
                    listOf(
                        ShortVacancyModel(
                            position = "Интерн",
                            salary = 50000,
                            companyName = "Семейный доктор",
                        ),
                        ShortVacancyModel(
                            position = "Секретарь",
                            salary = 20000,
                            companyName = "ИП Петров Игорь Михайлович",
                        ),
                        ShortVacancyModel(
                            position = "Врач-терапевт",
                            salary = 70000,
                            companyName = "АйБольно",
                        ),
                        ShortVacancyModel(
                            position = "Уборщик",
                            salary = 30000,
                            companyName = "ЛангОфф",
                        ),
                        ShortVacancyModel(
                            position = "Пекарь",
                            salary = 70000,
                            companyName = "Кухня дяди Васи",
                        ),
                    )
                ) { _, item ->
                    VacancyCard(vacancy = item, navController = navController)
                }
            }
        }
    }
}