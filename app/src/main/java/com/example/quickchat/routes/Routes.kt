package com.example.quickchat.routes

sealed class Routes(val route : String) {
    object AccountScreen : Routes("accountScreen")
     object ChatsScreen : Routes("chatsScreen")
     object LoginScreen : Routes("loginScreen")
    object MainScreen : Routes("mainScreen")
    object RegistrationScreen : Routes("registrationScreen")
    object WelcomeScreen : Routes("welcomeScreen")
    object ChatScreen : Routes("chatScreen")
    object UsernameQuickChatScreen : Routes ("usernameQuickChatScreen")
    object QuickChatThemesScreen : Routes("quickChatThemesScreen")
    fun withArgs(vararg arguments : String) : String
    {
        return buildString {
            append(route)

            arguments.forEach {
                append("/$it")
            }
        }
    }

}