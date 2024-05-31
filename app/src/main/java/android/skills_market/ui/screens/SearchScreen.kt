package android.skills_market.ui.screens

import android.skills_market.R
import android.skills_market.data.network.models.VacancyFilter
import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.navigation.extensions.noRippleClickable
import android.skills_market.ui.screens.custom_composables.VacancyCard
import android.skills_market.view_model.SearchUIState
import android.skills_market.view_model.event.SearchEvent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchUIState.Success,//TODO убрать Success
    navController: NavController,
    onEvent: (SearchEvent) -> Unit
) {
    val localContext = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = state.searchInput,
                        onValueChange = {
                            onEvent(SearchEvent.SetSearchInput(it))
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                onEvent(SearchEvent.GetVacancies)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search),
                                )
                            }
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
                            onEvent(SearchEvent.ShowFilterDialog)
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
                },
                modifier = Modifier.padding(vertical = 8.dp),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    itemsIndexed(
                        state.vacancies
                    ) { _, item ->
                        VacancyCard(
                            vacancy = item,
                            onClick = {
                                navController.navigate(
                                    route = Screen.VacancyScreen.route +
                                            "/${item.id}" +
                                            "/${item.position}" +
                                            "/${item.salary}" +
                                            "/${item.company.id}" +
                                            "/${item.company.name}" +
                                            "/${item.edArea}" +
                                            "/${item.formOfEmployment}" +
                                            "/${item.requirements}" +
                                            "/${item.location}" +
                                            "/${item.about.ifEmpty { " " }}" +
                                            "/${item.liked}"
                                ) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onRespond = {
                                onEvent(SearchEvent.RespondToVacancy(item.id))
                            },
                            onLike = {
                                onEvent(SearchEvent.ChangeLiked(item.id))
                            }
                        )
                    }
                }
            }
            if (state.showFilterDialog) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xCD000000))
                        .noRippleClickable {
                            onEvent(SearchEvent.ShowFilterDialog)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .noRippleClickable { }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Выберите фильтр")
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.currentFilter == VacancyFilter.None,
                                onClick = {
                                    onEvent(
                                        SearchEvent.SetVacanciesFilter(
                                            VacancyFilter.None
                                        )
                                    )
                                }
                            )
                            Text(
                                text = "Нет",
                                textAlign = TextAlign.Center
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = state.currentFilter != VacancyFilter.None,
                                    onClick = {
                                        onEvent(
                                            SearchEvent.SetVacanciesFilter(
                                                VacancyFilter.BySalary()
                                            )
                                        )
                                    }
                                )
                                Text(
                                    text = "Зарплата",
                                    textAlign = TextAlign.Center
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp, horizontal = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                OutlinedTextField(
                                    value = state.from.toString(),
                                    onValueChange = {
                                        onEvent(
                                            SearchEvent.SetFrom(it)
                                        )
                                    },
                                    label = {
                                        Text(text = "От")
                                    },
                                    modifier = Modifier.fillMaxWidth(0.4f),
                                    enabled = state.currentFilter != VacancyFilter.None
                                )
                                OutlinedTextField(
                                    value = state.to.toString(),
                                    onValueChange = {
                                        onEvent(
                                            SearchEvent.SetTo(it)
                                        )
                                    },
                                    label = {
                                        Text(text = "До")
                                    },
                                    modifier = Modifier.fillMaxWidth(0.8f),
                                    enabled = state.currentFilter != VacancyFilter.None
                                )
                            }
                        }
                        Button(
                            onClick = {
                                onEvent(
                                    if (state.currentFilter != VacancyFilter.None) {
                                        SearchEvent.SetVacanciesFilter(
                                            VacancyFilter.BySalary(
                                                from = state.from,
                                                to = state.to
                                            )
                                        )
                                    } else {
                                        SearchEvent.SetVacanciesFilter(
                                            VacancyFilter.None
                                        )
                                    }
                                )
                                onEvent(
                                    SearchEvent.GetVacancies
                                )
                                onEvent(SearchEvent.ShowFilterDialog)
                            },
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Text(text = "Готово")
                        }
                    }
                }
            }
        }
    }
}