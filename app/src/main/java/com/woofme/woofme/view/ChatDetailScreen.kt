package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woofme.woofme.R
import org.w3c.dom.Text

@Preview
@Composable
fun ChatDetailScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // fondo general
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(Color(0xFFABD3FA)) // mismo color que (171,211,250)
                .padding(top = 20.dp)
        ) {

            //Info de chat de la persona q estas hablando
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)

                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Ajustes",
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(R.drawable.perro3),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp) // cuadrado
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop // ajusta el recorte
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp), verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()


                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "eqwqwe",
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomStartPercent = 0,
                                    bottomEndPercent = 50
                                )
                            )
                            .background(Color(0xFF2563EB)) // rojo con transparencia
                            .padding(horizontal = 12.dp, vertical = 6.dp), // padding interno
                        color = Color.White // texto más legible
                    )
                }
                ChatBubble("qweeqw", true)
                ChatBubble("qweeqw", false)



            }
            //Enviar mensaje



        }
    }
}

@Composable
fun ChatBubble(text: String, isUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isUser) Spacer(modifier = Modifier.weight(1f))

        Text(
            "eqwqwe",
            modifier = Modifier
                .padding(8.dp)
                .clip(
                    RoundedCornerShape(
                        topStartPercent = 50,
                        topEndPercent = 50,
                        bottomStartPercent = if (isUser) 50 else 0,
                        bottomEndPercent = if (isUser) 0 else 50
                    )
                )
                .background(
                    if(!isUser)
                    Color(0xFF3E527D)
                    else
                        Color(0xFF2563EB)

                )
                .padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color.White
        )

        if (!isUser) Spacer(modifier = Modifier.weight(1f))
    }

}