package com.example.quickchat.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.quickchat.navigation.top_navigation.Tabs
import com.example.quickchat.navigation.top_navigation.TabsContent
import com.example.quickchat.navigation.top_navigation.TopBar
import com.example.quickchat.navigation.top_navigation.TopNavigationItem
import com.example.quickchat.ui.theme.QuickChatTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavController) {
    QuickChatTheme {
        val tabs = listOf(
            TopNavigationItem.QuickChatScreen,
            TopNavigationItem.ChatsScreen,
            TopNavigationItem.AccountScreen
        )
        var pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        Scaffold(
            backgroundColor = colors.secondaryVariant
        ) {
            Column() {
                Tabs(tabs = tabs, pagerState = pagerState)
                TabsContent(tabs = tabs, pagerState = pagerState)
            }

        }
    }
}



