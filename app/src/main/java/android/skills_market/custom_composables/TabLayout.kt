package android.skills_market.custom_composables

import android.skills_market.SearchActivity
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.TabBackgroundColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = 5)
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
//        BottomAppBar(
//            modifier = Modifier
//                .background(TabBackgroundColor)
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = "Sample Tab Text",
//                    style = TextStyle(color = WhiteFontColor),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(5.dp),
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
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
    HorizontalPager(state = pagerState) {
        page -> when(page) {
            0->SearchActivity()
        }
    }
}