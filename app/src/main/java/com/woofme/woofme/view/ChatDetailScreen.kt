package com.example.myapplication.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.woofme.woofme.R
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.ui.theme.LightBlue
import com.woofme.woofme.viewmodel.ChatDetailViewModel
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import kotlin.time.DurationUnit

@Composable
fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = viewModel(),
    onNavigate: () -> Unit

) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState() //Esto es para q la lista autoscrolee
    LaunchedEffect(state.messageList) {
        if (state.messageList.isNotEmpty()) {
            listState.animateScrollToItem(state.messageList.size - 1)
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // fondo general
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TopBarInfo(modifier, onNavigate)


            //Info de chat de la persona q estas hablando
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp), verticalArrangement = Arrangement.Top,
            ) {
                items(state.messageList)
                { message ->

                    ChatBubble(text = message.text, isUser = message.isUser, hour = message.hour)
                }


            }
            //Enviar mensaje
            TextFieldSendMessage({ viewModel.sendMessage() }, viewModel)
            Spacer(modifier = Modifier.height(10.dp))


        }
    }
}

@Composable
fun TextFieldSendMessage(sendMessage: () -> Unit, viewModel: ChatDetailViewModel) {
    val state = viewModel.uiState.collectAsState()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        OutlinedTextField(
            value = state.value.inputMessage,
            onValueChange = { viewModel.onMessageTextChanged(it) },
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
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(1f)
                .border(
                    BorderStroke(2.dp, LightBlue),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .clip(CircleShape)
                .background(DarkBlue)
                .padding(2.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.clickable { sendMessage() })
        }

    }


}

@Composable
fun TopBarInfo(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            .background(LightBlue)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver a atrás",
            modifier = Modifier
                .size(24.dp)
                // Ahora no necesita .align porque la Row completa está centrada
                .clickable {
                    onNavigate()
                }
        )
        Image(
            painter = painterResource(R.drawable.perro3),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    "Pepe",
                    color = Color(13, 27, 42, 255),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text("Online", fontSize = 14.sp, color = Color(107, 114, 128, 255))
            }

        }

    }
}


@Preview()
@Composable
fun ChatBubble(text: String = "HOla", isUser: Boolean = true, hour: LocalTime = LocalTime.now()) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 15.dp,
                        bottomEnd = 15.dp,
                        topStart= if (isUser) 15.dp else 0.dp,
                        topEnd = if (isUser) 0.dp else 15.dp
                    )
                )
                .background(
                    if (!isUser) Color(0xFF3E527D) else Color(0xFF2563EB)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)

        ) {


            Text(
                text = text,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(2.dp))


            Text(
                text = hour.truncatedTo(ChronoUnit.MINUTES).toString(),
                color = Color.White.copy(alpha = 0.7f), // Un poco más tenue
                fontSize = 12.sp
            )
        }
    }
        }






