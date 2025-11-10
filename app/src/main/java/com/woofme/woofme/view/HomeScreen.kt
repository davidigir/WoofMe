package com.example.myapplication.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.woofme.woofme.R
import com.woofme.woofme.model.Profile
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.viewmodel.HomeScreenViewModel

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               viewModel: HomeScreenViewModel = viewModel()

) {

    val state by viewModel.uiState.collectAsState()


    var selectedItem by remember { mutableStateOf(0) }
    Column(modifier = modifier.fillMaxSize()
        .background(Color.White)
    ) {
        Text(
            "WoofMe",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )

        LazyColumn () {
            items(state.profileDetails) { profile ->
                ProfileDisplay(profile)
            }
        }


        }

}

@Composable
fun ProfileDisplay(profile: Profile) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
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


                AsyncImage(
                    model = profile.profileImageRes,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp) // cuadrado
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop // ajusta el recorte
                )

                Column {
                    Text(
                        profile.name,
                        color = Color(13, 27, 42, 255),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text("ID ${profile.id}", color = Color(107, 114, 128, 255))

                }


            }

            Box(

            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    model = profile.images[0],
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

}