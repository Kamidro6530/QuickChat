package com.example.quickchat.viewmodels.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor(): ViewModel(){


    private val _usernameTextfromField = mutableStateOf("")//Nazwa użytkownika pobrana z pola textField
    //State oznacza stan || Jeśli stan obiektu się zmieni (przykładowo nazwa użytkownika ulegnie zmianie) widok się odświeży
    val usernameText : State<String> = _usernameTextfromField //Publiczna niezmienna wersja  przechowująca nazwe użytkownika

    private val _onJoinChat = MutableSharedFlow<String>() //SharedFlow oznacza współdzielony strumień danych (jeden strumień danych dla wielu konsumentów)
    val onJoinChat = _onJoinChat.asSharedFlow()

    //Gdy następuje zmiana nazwy użytkownika
    fun onUsernameChange(username : String){
        _usernameTextfromField.value = username
    }
    //Po wciśnięciu przez użytkownika przycisku Join
    fun onJoinClick(){
        //viewModelScope wątek się zatrzymuję po wywołaniu onCleared(po zakończeniu cyklu życia aktywności)
        viewModelScope.launch {
            if (usernameText.value.isNotBlank()){
                _onJoinChat.emit(usernameText.value)
            }
        }
    }
}