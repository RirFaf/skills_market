package android.skills_market.custom_composables

import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.TabBackgroundColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationBar() {
    val pagerState = rememberPagerState(pageCount = 5)
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Search" to Icons.Default.Search,
        "Favourite" to Icons.Default.Favorite,
        "Response" to Icons.Default.Email,
        "Chat" to Icons.Default.Lock,
        "Profile" to Icons.Default.Person
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = TabBackgroundColor,
        contentColor = AccentBlue,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = AccentBlue
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                icon = { Icon(imageVector = list[index].second, contentDescription = null) },
                text = {
                    Text(
                        list[index].first,
                        color = if (pagerState.currentPage == index) AccentBlue else Color.Gray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
//            1 -> StudentSearchActivity()
        }
    }
}