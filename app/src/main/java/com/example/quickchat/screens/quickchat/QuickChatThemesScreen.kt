package com.example.quickchat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quickchat.navigation.top_navigation.TopNavigationItem
import com.example.quickchat.routes.Routes
import com.example.quickchat.screens.chat.ChatScreen
import com.example.quickchat.screens.quickchat.ThemeItem
import com.example.quickchat.screens.quickchat.UsernameQuickChatScreen
import com.example.quickchat.ui.theme.*
import com.example.quickchat.viewmodels.chat.ChatViewModel



@Composable
fun QuickChatThemesScreen(navController: NavController) {

    val listOfThemes : List<ThemeItem> = listOf(
        ThemeItem("Policy", policy),
        ThemeItem("Memes", memes),
        ThemeItem("IT", it),
        ThemeItem("Sport", sport),
        ThemeItem("Films", films),
        ThemeItem("Books", books),
        ThemeItem("Games", games),
        ThemeItem("Lifestyle", lifestyle),
        ThemeItem("Clothes", clothes),
        ThemeItem("Work", work),
        ThemeItem("Help", help)
    )


    Column() {
        Row() {
            LazyColumn(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)) {

               items(listOfThemes){
                    ThemeRow(it.name, it.color,navController = navController)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp))
                }
            }
        }
    }
}

@Composable
fun ThemeRow(nameOfTheme: String,themeColor : Color,navController: NavController) {
    Box(
        Modifier
            .clip(MaterialTheme.shapes.large)
            .background(themeColor)
            .fillMaxWidth()

            .clickable {
            navController.navigate(Routes.UsernameQuickChatScreen.withArgs(nameOfTheme,themeColor.toString()))
            }, contentAlignment = Alignment.Center

    ) {

        Row(horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.padding(8.dp),fontSize = 24.sp,text = nameOfTheme)
        }

    }
}

@Composable
fun ListOfThemes() {

}