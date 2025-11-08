package com.woofme.woofme.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.woofme.woofme.viewmodel.ChatViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.woofme.woofme.model.Chat
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.ui.theme.LightBlue
import com.woofme.woofme.viewmodel.ChatTabEnum

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel(),
    onNavigate: ()->Unit
){
    //estado de la screen en el viewModel
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(255, 255, 255))
            .padding(20.dp)
    ) {

        //titulo
        Text("Chats", color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(10.dp))

        Spacer(modifier = Modifier.height(20.dp))


        //Selector de Busqueda
        SearchBar(
            modifier = Modifier,
            searchQuery = state.searchQuery,
            onQueryChange = viewModel::onSearchQueryChanged

        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- Selector de Pestañas ---
        TabSelector(
            selectedTab = state.selectedTab, // Usa el estado
            onTabSelected = viewModel::onTabSelected
        )




        Spacer(modifier = Modifier.height(16.dp))

        // --- Lista de Chats ---
        LazyColumn {items(state.chatList){
            chat->
            ChatItemRow(
                chat = chat,
                onClick = { onNavigate() }
            )

        }

        }
    }
}

@Composable
fun TabSelector(selectedTab: ChatTabEnum, onTabSelected: (ChatTabEnum) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .background(color = LightBlue)
    ) {
        // Tab 1: Chats
        TabButton(
            text = "Todos",
            isSelected = selectedTab == ChatTabEnum.ALL,
            onClick = { onTabSelected(ChatTabEnum.ALL) },
            modifier = Modifier.weight(1f)
        )
        // Tab 2: Grupos
        TabButton(
            text = "Grupos",
            isSelected = selectedTab == ChatTabEnum.GROUPS,
            onClick = { onTabSelected(ChatTabEnum.GROUPS) },
            modifier = Modifier.weight(1f)
        )
        // Tab 3: Contactos
        TabButton(
            text = "Contactos",
            isSelected = selectedTab == ChatTabEnum.CONTACTS,
            onClick = { onTabSelected(ChatTabEnum.CONTACTS) },
            modifier = Modifier.weight(1f)
        )
    }
}

// Composable para el botón de pestaña (para reusabilidad)
@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) DarkBlue else LightBlue)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

// Composable para una fila de chat ()
@Composable
fun ChatItemRow(chat: Chat, onClick: () -> Unit, modifier: Modifier = Modifier) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp)
            .clickable(onClick = onClick), // Haz clic en toda la fila
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = chat.profileImageRes, // Pasa la URI String directamente
            contentDescription = chat.name,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    chat.name,
                    color = Color(13, 27, 42, 255),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    chat.lastMessage,
                    fontSize = 14.sp,
                    color = Color(107, 114, 128, 255)
                )
            }
            Text(
                chat.lastMessageTime,
                fontSize = 14.sp,
                color = Color(107, 114, 128, 255)
            )
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              searchQuery: String,
              onQueryChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)

    ){

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onQueryChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth().border(
                    BorderStroke(2.dp, LightBlue),
                    shape = CircleShape
                )
            )

    }




}




