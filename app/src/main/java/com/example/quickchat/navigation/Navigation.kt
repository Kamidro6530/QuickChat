package com.example.quickchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.quickchat.routes.Routes
import com.example.quickchat.screens.*
import com.example.quickchat.screens.chat.ChatScreen
import com.example.quickchat.screens.quickchat.UsernameQuickChatScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController) {


    NavHost(navController, Routes.MainScreen.route) {

        composable(Routes.LoginScreen.route) { LoginScreen() }

        composable(Routes.RegistrationScreen.route) { RegistrationScreen() }

        composable(Routes.WelcomeScreen.route) { WelcomeScreen() }

        composable(Routes.MainScreen.route) { MainScreen(navController) }


        composable(Routes.ChatsScreen.route) { Chats() }

        composable(Routes.AccountScreen.route) { Account() }

        composable(Routes.QuickChatThemesScreen.route) {
            QuickChatThemesScreen(navController = navController)
        }

        composable(
            Routes.ChatScreen.route + "/{username}",
            arguments = listOf(navArgument("username") {})
        ) { entry -> ChatScreen(username = entry.arguments?.getString("username")) }

        composable(
            Routes.UsernameQuickChatScreen.route + "/{name}/{color}", arguments = listOf(
                navArgument("name") {},
                navArgument("color") {})
        ){ entry->
                UsernameQuickChatScreen(
                    navController = navController,
                    nameOfTheme = entry.arguments?.getString("name")!!,
                    colorOfTheme = entry.arguments!!.getString("color"))
            }
    }
}