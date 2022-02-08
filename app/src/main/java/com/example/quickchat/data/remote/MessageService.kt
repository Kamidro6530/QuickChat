package com.example.quickchat.data.remote

import com.example.quickchat.domain.model.Message

interface MessageService {

    suspend fun getAllMessages() : List<Message>

    companion object {
        //Stały podstawowy adress
        const val BASE_URL = "http://10.0.2.2:8080"
    }

    //Adresy url do każdej metody z service
    sealed class Endpoints(val url : String)
    {
        object GetAllMessages : Endpoints("$BASE_URL/messages")
    }
}