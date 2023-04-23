package android.skills_market.custom_composables

import android.skills_market.ui.theme.ButtonColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        "Поиск" to Icons.Default.Search,
        "Избранное" to Icons.Default.Favorite,
        "Отклики" to Icons.Default.Email,
        "Чаты" to Icons.Default.Lock,
        "Профиль" to Icons.Default.Person
    )
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = Modifier.fillMaxHeight(0.08f),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = ButtonColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                icon = {
                    Icon(
                        imageVector = list[index].second,
                        contentDescription = null,
                        tint = if (pagerState.currentPage == index) Color.Yellow else Color.Red
                    )
                },
                text = {
                    Text(
                        text = list[index].first,
                        modifier = Modifier.fillMaxWidth(),
                        color = if (pagerState.currentPage == index) Color.Yellow else Color.Red,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
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
//            1 -> localContext.startActivity(
//                Intent(
//                    localContext,
//                    StudentSearchActivity::class.java
//                )
//           )
//            2 -> localContext.startActivity(
//                Intent(
//                    localContext,
//                    StudentSearchActivity::class.java
//                )
//            )
//            3 -> localContext.startActivity(
//                Intent(
//                    localContext,
//                    ResponseActivity::class.java
//                )
//            )
//            4 -> localContext.startActivity(
//                Intent(
//                    localContext,
//                    ChatListActivity::class.java
//                )
//            )
//            5 -> localContext.startActivity(
//                Intent(
//                    localContext,
//                    ProfileScreen::class.java
//                )
//            )
        }
    }
}