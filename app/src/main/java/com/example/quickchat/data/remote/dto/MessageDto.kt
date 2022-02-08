package com.example.quickchat.data.remote.dto

import com.example.quickchat.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*


//DTO - obiekt transferu  danych
//Obiekt tej klasy służy do transferu między serwerem a aplikacją
@Serializable
data class MessageDto(
    val text : String,
    val timestamp : Long,
    val username : String,
    val id : String
){

    //Mapuje z Dto do zwykłego obiektu używanego w aplikacji
    fun toMessage() : Message {
        val date = Date(timestamp)
        val formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT ).format(date)
        return Message(
            text = text,
            formattedTime =  formattedDate,
            username = username
        )
    }

}
