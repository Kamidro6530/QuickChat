package com.example.quickchat.data.remote

import com.example.quickchat.domain.model.Message
import com.example.quickchat.util.Resource
import kotlinx.coroutines.flow.Flow


//Metody jakimi będzie się posługiwała aplikacja komunikując się z serwerem w czasie połączenia
interface ChatSocketService {


    suspend fun initSession(
        username : String
    ) : Resource<Unit>

    suspend fun  sendMessage(message : String)


     fun observeMessages() : Flow<Message>

     suspend fun closeSession()

    companion object {
        //Stały podstawowy adress
        const val BASE_URL = "ws://10.0.2.2:8080"
    }

    //Adresy url do każdej metody z service
    sealed class Endpoints(val url : String)
    {
        object ChatSocket : Endpoints("$BASE_URL/chat-socket")
    }
}