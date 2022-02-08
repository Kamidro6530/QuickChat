package com.example.quickchat.screens.quickchat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quickchat.routes.Routes
import com.example.quickchat.ui.theme.ghost_white
import com.example.quickchat.ui.theme.maximum_blue_purple
import com.example.quickchat.ui.theme.medium_purple
import com.example.quickchat.viewmodels.user.UsernameViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UsernameQuickChatScreen(
    navController: NavController,
    viewModel: UsernameViewModel = hiltViewModel(),
    nameOfTheme: String,
    colorOfTheme: String?
) {



    LaunchedEffect(key1 = true){
        viewModel.onJoinChat.collectLatest { username ->
            navController.navigate(Routes.ChatScreen.withArgs(username))
        }
    }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .background(maximum_blue_purple)
            .padding(42.dp)
            .fillMaxSize()
    ) {


            Text(text = "Enter your username", textAlign = TextAlign.Center, fontSize = 32.sp,modifier = Modifier.padding(top = 70.dp),color = ghost_white)
    
            Spacer(Modifier.height(100.dp))

            TextField(
                value = viewModel.usernameText.value,
                onValueChange = viewModel::onUsernameChange,
                placeholder = {
                    Text(text = "Enter a username...")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(textColor = medium_purple,disabledTextColor = medium_purple,backgroundColor = ghost_white,cursorColor = medium_purple,placeholderColor = medium_purple)

            )


            Button(
                onClick = { viewModel.onJoinClick() },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ghost_white,contentColor = medium_purple)
            ) {
                Text(text = "Join", fontSize = 18.sp)
            }
        
    }
}