package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woofme.woofme.R

@Preview
@Composable
fun HomeScren(modifier: Modifier = Modifier) {

    val dogsImages = List(15) { R.drawable.perro2 }
    val dogsPerfiles = List(15) { R.drawable.perro2 }


    var selectedItem by remember { mutableStateOf(0) }
    Box(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFABD3FA))
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp) // suficiente altura para burbuja + texto

                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    Image(
                        painter = painterResource(R.drawable.perro3),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp) // cuadrado
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop // ajusta el recorte
                    )

                    Column {
                        Text(
                            "Pepe",
                            color = Color(13, 27, 42, 255),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Text("ID #123123", color = Color(107, 114, 128, 255))

                    }


                }

                Box(

                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                        painter = painterResource(dogsImages[0]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Box(

                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(32.dp)
                            .background(Color(0x48000000))


                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Send,
                                contentDescription = "Favorito",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(28.dp)
                                    .rotate(-45f)
                            )

                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = "Favorito",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)

                            )
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = "Favorito",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)

                            )


                        }

                    }

                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(70.dp) // suficiente altura para burbuja + texto
                .background(Color(0xFFABD3FA))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                // Iconos uno a uno
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedItem = 0 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 55.dp, height = 30.dp)
                            .background(
                                color = if (selectedItem == 0) Color(0xFF2563EB) else Color.Transparent,
                                shape = RoundedCornerShape(45.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            tint = if (selectedItem == 0) Color.White else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        "Inicio",
                        color = if (selectedItem == 0) Color.Gray else Color.Gray,
                        fontWeight = if (selectedItem == 0) FontWeight.Bold else FontWeight.Normal

                    )
                }



                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedItem = 1 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 55.dp, height = 30.dp)
                            .background(
                                color = if (selectedItem == 1) Color(0xFF2563EB) else Color.Transparent,
                                shape = RoundedCornerShape(50.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Face,
                            contentDescription = "Perfil",
                            tint = if (selectedItem == 1) Color.White else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Perfil",
                        color = if (selectedItem == 1) Color.White else Color.Gray,
                        fontWeight = if (selectedItem == 1) FontWeight.Bold else FontWeight.Normal


                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedItem = 2 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 55.dp, height = 30.dp)
                            .background(
                                color = if (selectedItem == 2) Color(0xFF2563EB) else Color.Transparent,
                                shape = RoundedCornerShape(50.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Ajustes",
                            tint = if (selectedItem == 2) Color.White else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Ajustes",
                        color = if (selectedItem == 2) Color.White else Color.Gray,
                        fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}