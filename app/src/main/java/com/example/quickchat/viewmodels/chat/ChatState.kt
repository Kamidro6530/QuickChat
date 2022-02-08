package com.example.quickchat.viewmodels.chat

import com.example.quickchat.domain.model.Message

//Stan chatu (State)
data class ChatState (
    val messages : List<Message> = emptyList(),
    val isLoading : Boolean = false
        )