package com.example.quickchat.screens.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.quickchat.ui.theme.*
import com.example.quickchat.viewmodels.chat.ChatViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable

fun ChatScreen(username: String?, chatViewModel: ChatViewModel = hiltViewModel()) {


    val context = LocalContext.current
    LaunchedEffect(key1 = true) { //Instrukcję uruchamiają się tylko za pierwszym załadowaniem kompozycji
        chatViewModel.toastEvent.collectLatest { text ->
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) { //Instrukcje uruchamiają gdy zmienia się klucz w tym przypadku gdy wywoła się zdarzenie OnStart lub OnStop
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                chatViewModel.connectToChat()
            } else if (event == Lifecycle.Event.ON_STOP) {
                chatViewModel.disconnect()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)//Dodaje obserwatora który sprawdza czy stan cyklu życia się zmienił
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val chatState = chatViewModel.chatState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(antique_white)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(chatState.messages) { message ->
                val isOwnMessage = message.username == username //Wiadomośc pojawi się w inny sposób w zależności kto jest autorem wiadomości
                Box(
                    contentAlignment = if (isOwnMessage) {
                        Alignment.CenterEnd
                    } else Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .background(
                                color = if (isOwnMessage) medium_purple else gray_message,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = message.username,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Text(
                            text = message.text,
                            color = Color.White
                        )
                        Text(
                            text = message.formattedTime,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 8.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        Row(
            modifier = Modifier
                .background(antique_white)
                .clip(MaterialTheme.shapes.large)
        ) {
            Surface(shape = MaterialTheme.shapes.large,modifier = Modifier.padding(5.dp)) {
                TextField(
                    value = chatViewModel.messageText.value,
                    onValueChange = chatViewModel::onMessageChange,
                    textStyle = TextStyle(fontSize = 12.sp),
                    placeholder = {
                        Text(text = "Enter a message",fontSize = 12.sp)
                    },trailingIcon = {

                        IconButton(onClick = chatViewModel::sendMessage  ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send",
                                tint = unbleached_silk
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = medium_purple,
                        disabledTextColor = medium_purple,
                        backgroundColor = ghost_white,
                        cursorColor = medium_purple,
                        placeholderColor = medium_purple,
                        focusedIndicatorColor = ghost_white,
                        unfocusedIndicatorColor = ghost_white,
                        disabledIndicatorColor = ghost_white
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                    )

            }


        }
    }
}