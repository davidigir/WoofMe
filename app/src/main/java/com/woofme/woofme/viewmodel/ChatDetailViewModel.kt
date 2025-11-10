package com.woofme.woofme.viewmodel

import androidx.lifecycle.ViewModel
import com.woofme.woofme.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime


data class ChatDetailUiState(
    val messageList: List<Message> = emptyList(),
    val inputMessage: String = ""

)

class ChatDetailViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(ChatDetailUiState())
    val uiState: StateFlow<ChatDetailUiState> = _uiState.asStateFlow()

    init {
        loadInitialMessages()
    }

    fun loadInitialMessages(){
        val initialMessages = MessageDataMock.getInitialMessages()
        _uiState.value = _uiState.value.copy(messageList = initialMessages)
    }
    fun sendMessage(){
        val text = _uiState.value.inputMessage.trimEnd().trimStart()
        if (text.isEmpty()) {return}
        val currentMessages = _uiState.value.messageList
        val message: Message = Message(text= text, hour = LocalTime.now(), isUser = true)
        _uiState.update{
            currentState->
            currentState.copy(
                messageList = currentMessages + message,
                inputMessage = ""
            )
        }


    }

    fun onMessageTextChanged(text: String){
        _uiState.update{
            currentState->
            currentState.copy(inputMessage = text)

        }
    }



}

object MessageDataMock{
    fun getInitialMessages(): List<Message>{
        return listOf(
            Message(
                text = "A q hora te biene bien??",
                hour = LocalTime.now(),
                isUser = true
            ),
                    Message(
                    text = "A las 5",
            hour = LocalTime.now(),
            isUser = false
        ),
                            Message(
                            text = "Perfecto!",
            hour = LocalTime.now(),
            isUser = true
        )

        )

    }

}