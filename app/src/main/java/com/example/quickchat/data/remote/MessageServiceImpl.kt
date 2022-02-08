package com.example.quickchat.data.remote

import com.example.quickchat.data.remote.dto.MessageDto
import com.example.quickchat.domain.model.Message
import io.ktor.client.*
import io.ktor.client.request.*
import java.lang.Exception

//Implementacja serwisu do pobierania wszystkich wiadomośći(Po połączeniu użytkownika z pokojem)
class MessageServiceImpl(private val client: HttpClient) : MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {
            //Klient pobiera listę wiadomości transferu danych  i parsuje na wiadomości używane w apikacji
            client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url )
                .map {
                    it.toMessage()
                }
        }catch (e : Exception){
            //W przypadku błędu zwraca pustą liste
            emptyList<Message>()
        }
    }
}