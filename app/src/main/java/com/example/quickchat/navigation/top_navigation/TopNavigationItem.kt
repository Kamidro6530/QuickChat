package com.example.quickchat.navigation.top_navigation

import androidx.compose.runtime.Composable
import com.example.quickchat.screens.Chats






sealed class TopNavigationItem (
    val name : String,
    val routes : String
        ){
    object QuickChatScreen : TopNavigationItem("QuickChat","quickChatScreen")
    object ChatsScreen : TopNavigationItem("Chat","chatScreen")
    object AccountScreen : TopNavigationItem("Account","accountScreen")
}