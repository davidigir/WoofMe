package com.woofme.woofme.viewmodel

import androidx.lifecycle.ViewModel
import com.woofme.woofme.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class ChatDetailUiState(
    val messageList: List<Message> = emptyList(),
    val inputMessage: String = ""

)

class ChatDetailViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(ChatDetailUiState())
    val uiState: StateFlow<ChatDetailUiState> = _uiState.asStateFlow()


    fun loadMessages(){

    }
    fun sendMessage(){

    }



}