package com.example.quickchat.data.remote

import com.example.quickchat.data.remote.dto.MessageDto
import com.example.quickchat.domain.model.Message
import com.example.quickchat.util.Resource
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception


//Implementacja serwisu do obsługi połączenia i metod z nim związanych
class ChatSocketServiceImpl(private val client : HttpClient) : ChatSocketService {

    //Służy do utworzenia klienta web socket
    private var socket : WebSocketSession? = null

    //inicjacja połączenia z serwerem
    override suspend fun initSession(username: String): Resource<Unit> {
        return try {
            //Tworzy sesję łącząc się z socketem
            socket = client.webSocketSession {
                url("${ChatSocketService.Endpoints.ChatSocket.url}?username=$username")
            }
            if(socket?.isActive == true){
                Resource.Success(Unit)
            }else{
                Resource.Error("Couldn't establish a connection ")
            }
        }catch (e : Exception) {
            e.printStackTrace()
           Resource.Error(e.localizedMessage ?: "Unknown error ")
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        }catch (e : Exception){
            e.printStackTrace()

        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()//Odbieraj jako typ Flow
                ?.filter { it is Frame.Text }//Przepuszczaj tylko te date które są Tekstem
                ?.map {
                  val json = (it as? Frame.Text)?.readText() ?: "" //Przechowuj obiekt jako tekst w przypadku błędu zwróć pusty String
                    val messageDto = Json.decodeFromString<MessageDto>(json) // Zamienia stirng (json) na obiekt
                    messageDto.toMessage()
                } ?: flow {  }
        }catch (e : Exception){
            e.printStackTrace()
            flow {  }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}