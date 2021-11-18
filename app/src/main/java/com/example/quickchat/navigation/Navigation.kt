package com.example.quickchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.quickchat.Routes
import com.example.quickchat.screens.Account
import com.example.quickchat.screens.Chats
import com.example.quickchat.screens.MainScreen
import com.example.quickchat.screens.QuickChat
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController) {


   NavHost(navController,Routes.MainScreen.route){

      composable(Routes.MainScreen.route){
         MainScreen(navController)
      }

      composable(Routes.QuickChatScreen.route){
      QuickChat()
      }

      composable(Routes.ChatsScreen.route){
      Chats()
      }
      composable(Routes.AccountScreen.route){
      Account()
      }

   }
}