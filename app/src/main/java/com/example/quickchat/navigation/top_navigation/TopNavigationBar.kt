package com.example.quickchat.navigation.top_navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickchat.screens.Account
import com.example.quickchat.screens.Chats
import com.example.quickchat.screens.QuickChatThemesScreen
import com.example.quickchat.screens.WelcomeScreen
import com.example.quickchat.screens.chat.ChatScreen
import com.example.quickchat.screens.quickchat.UsernameQuickChatScreen
import com.example.quickchat.ui.theme.QuickChatTheme
import com.example.quickchat.ui.theme.medium_purple
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "QuickChat", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.background
    )
}

@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TopNavigationItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = medium_purple,
        contentColor = MaterialTheme.colors.background,
        indicator = { position ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, position)
            )
        }) {
        tabs.forEachIndexed { index, topNavigationItem ->
            Tab(text = { Text(topNavigationItem.name) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                })


        }
    }

}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TopNavigationItem>, pagerState: PagerState,navController: NavController) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        when (page) {
            0 -> {
             QuickChatThemesScreen(navController = navController)
            }
            1 -> {
                Chats()
            }
            2 -> {
                Account()
            }
        }

    }
}


/*TabRow(selectedTabIndex = tabIndex) {
        tabs.forEachIndexed { index, item ->
            Tab(selected = tabIndex == index, onClick = {
                tabIndex = index
                when (index) {
                    0 -> {QuickChat()}
                    1 -> {navController.navigate(Routes.ChatsScreen.route)}
                    2 -> {navController.navigate(Routes.AccountScreen.route)}
                }
            }, text = {
                Text(text = item.name)
            })
        }
    }*/