package com.woofme.woofme.viewmodel

import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.woofme.woofme.R
import com.woofme.woofme.model.APP_PACKAGE_NAME
import com.woofme.woofme.model.Chat
import com.woofme.woofme.model.ChatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ChatUiState(
    val selectedTab: Int = 1, //Esto deberia ser un en
    val chatList: List<Chat> = emptyList()
)

class ChatViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init{
        loadInitialChats()
    }
    val id = R.drawable.perro3
    private fun loadInitialChats(){
        val initialChats = ChatData.getInitialChats()
        _uiState.value = _uiState.value.copy(chatList = initialChats)

    }
    fun onTabSelected(tabIndex: Int){
        _uiState.update{
            currentState -> currentState.copy(selectedTab = tabIndex)

        }
    }
    fun onChatClicked(chat: Chat){
        println("Chjat clicki ${chat.name}")
    }


}