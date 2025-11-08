package com.woofme.woofme.viewmodel

import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.woofme.woofme.R
import com.woofme.woofme.model.APP_PACKAGE_NAME
import com.woofme.woofme.model.Chat
import com.woofme.woofme.model.ChatDataMock
import com.woofme.woofme.model.ChatType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

data class ChatUiState(
    val selectedTab: ChatTabEnum = ChatTabEnum.ALL, //Esto deberia ser un enum
    val chatList: List<Chat> = emptyList(),
    val initialChatList: List<Chat> = emptyList(),
    val searchQuery: String = ""
)

enum class ChatTabEnum{
    ALL,
    GROUPS,
    CONTACTS
}

class ChatViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init{
        loadInitialChats()
    }
    private fun loadInitialChats(){
        val initialChats = ChatDataMock.getInitialChats()
        _uiState.value = _uiState.value.copy(chatList = initialChats, initialChatList = initialChats)

    }
    fun filterChats(){
        val currentChats = _uiState.value.chatList

        _uiState.update{
            currentState->
            currentState.copy(
                chatList = currentChats.filter {
                    it.name.contains(_uiState.value.searchQuery, ignoreCase = true)

                })
                }
    }

    private fun searchQuery(query: String){
        val chatList = _uiState.value.initialChatList.filter{
            it.name.contains(query, ignoreCase = true)

        }
    }
    private fun updateChatList(state: ChatUiState): List<Chat> {
        var chatList =when(state.selectedTab) {
            ChatTabEnum.ALL -> _uiState.value.initialChatList
            ChatTabEnum.GROUPS -> _uiState.value.initialChatList.filter { it.typeChat == ChatType.GROUP }
            ChatTabEnum.CONTACTS -> _uiState.value.initialChatList.filter { it.typeChat == ChatType.CONTACT }

        }
        chatList = chatList.filter{
            it.name.contains(state.searchQuery, ignoreCase = true)
        }
        return chatList
    }
    fun onSearchQueryChanged(query: String){
        _uiState.update{ currentState ->
            val newState = currentState.copy(searchQuery = query)

            val chatListFiltered = updateChatList(newState)

            newState.copy(chatList = chatListFiltered)
        }
    }
    fun onTabSelected(tabState: ChatTabEnum){
        _uiState.update{ currentState ->

            val newChatList = updateChatList(
                currentState.copy(selectedTab = tabState)
            )

            currentState.copy(
                selectedTab = tabState,
                chatList = newChatList
            )
        }

        }

    fun onChatClicked(chat: Chat){
        println("Chjat clicki ${chat.name}")
    }

}
