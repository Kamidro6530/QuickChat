package com.example.quickchat

sealed class Routes(val route : String) {
    object AccountScreen : Routes("accountScreen")
     object ChatsScreen : Routes("chatsScreen")
     object LoginScreen : Routes("loginScreen")
    object MainScreen : Routes("mainScreen")
    object QuickChatScreen : Routes("quickChatScreen")
    object RegistrationScreen : Routes("registrationScreen")
    object WelcomeScreen : Routes("welcomeScreen")

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