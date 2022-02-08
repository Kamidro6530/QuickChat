package com.example.quickchat.viewmodels.chat

import android.graphics.Insets.add
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickchat.data.remote.ChatSocketService
import com.example.quickchat.data.remote.MessageService
import com.example.quickchat.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, //Przechowuje zapisany stan aby nie musieć go podawać z widoku (SavedStateHandle może być wstrzyknięty przez hilt)
    private val chatSocketService: ChatSocketService,
    private val messageService: MessageService
) : ViewModel() {



    private val _messageText =
        mutableStateOf<String>("")//Domyślna wartość pola do wpisywania wiadomości
    val messageText: State<String> = _messageText

    private val _chatState = mutableStateOf(ChatState())//Stan chatu
    val chatState: State<ChatState> = _chatState

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun onMessageChange(message : String){ //Zmiana wiadomośći
        _messageText.value = message
    }

    fun connectToChat(){
        getAllMessages()
        savedStateHandle.get<String>("username")?.let { username ->
            viewModelScope.launch {
                val result = chatSocketService.initSession(username) //Przechowuję rezultat utworzonej sesji
                when(result){
                    is Resource.Success -> {
                    chatSocketService.observeMessages().onEach { message -> //Każda wiadomość która zostanie odebrana przez flow zostaje dodana do listy wiadomości w pokoju chatu
                        val newList = chatState.value.messages.toMutableList().apply {
                            add(0,message)
                        }
                        _chatState.value = chatState.value.copy(messages = newList)
                    }.launchIn(viewModelScope) //Nakazuję uruchomić w viewModelScope aby po wykonaniu onCleared zakończyć obserwowanie wiadomości i dodawanie ich do listy


                    }
                    is Resource.Error -> {
                    _toastEvent.emit(result.message ?: "Unknown message")
                    }
                }
            }
        }
    }

    // Zamknięcie sesji web socket
    fun disconnect(){
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }
    fun getAllMessages(){
        viewModelScope.launch {
            _chatState.value = chatState.value.copy(isLoading = true)
            val result = messageService.getAllMessages()
            _chatState.value = chatState.value.copy(
                isLoading = false,
                messages = result
            )
        }
    }

    fun sendMessage(){
        viewModelScope.launch {
            if (messageText.value.isNotBlank())
            chatSocketService.sendMessage(messageText.value)
            _messageText.value = "" //Czyści TextField po wysłaniu wiadomości
        }
    }


    override fun onCleared() {
        super.onCleared()
        disconnect()
    }



}