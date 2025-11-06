package com.woofme.woofme.view

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.woofme.woofme.viewmodel.ChatViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.woofme.woofme.model.Chat
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.ui.theme.LightBlue

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel()
){

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(255, 255, 255))
            .padding(20.dp)
    ) {
        // --- Selector de Pestañas ---
        TabSelector(
            selectedTab = state.selectedTab, // Usa el estado
            onTabSelected = viewModel::onTabSelected // Llama al evento del ViewModel
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Lista de Chats ---
        Column {
            state.chatList.forEach { chat ->
                ChatItemRow(
                    chat = chat,
                    onClick = { viewModel.onChatClicked(chat) } // Llama al evento del ViewModel
                )
            }
        }
    }
}

// Composable para el selector de pestañas (puede ser extraído)
@Composable
fun TabSelector(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .background(color = LightBlue)
    ) {
        // Tab 1: Chats
        TabButton(
            text = "Chats",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f)
        )
        // Tab 2: Grupos
        TabButton(
            text = "Grupos",
            isSelected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            modifier = Modifier.weight(1f)
        )
        // Tab 3: Contactos
        TabButton(
            text = "Contactos",
            isSelected = selectedTab == 3,
            onClick = { onTabSelected(3) },
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

// Composable para una fila de chat (para reusabilidad)
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

// Preview solo para la vista
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    // En la preview, puedes instanciar el ViewModel o usar uno falso si es muy complejo
    ChatScreen()
}