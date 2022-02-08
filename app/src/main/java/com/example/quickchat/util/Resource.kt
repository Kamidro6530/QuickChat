package com.example.quickchat.util

//Util - użyteczne/wykorzystanie
//Klasa służąca do odróżnienia zwracanych danych w zależności od wyniku zapytania
sealed class Resource<T>(val data : T? = null,val message : String? = null  ){
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)

}
