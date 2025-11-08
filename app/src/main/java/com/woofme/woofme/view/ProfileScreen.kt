package com.example.myapplication.screen

import android.app.Activity
import android.view.RoundedCorner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import kotlinx.coroutines.flow.distinctUntilChanged
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.woofme.woofme.R
import com.woofme.woofme.viewmodel.ProfileViewModel
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.woofme.woofme.common.BottomBar
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.ui.theme.LightBlue
import com.yalantis.ucrop.UCrop
import java.io.File


@Preview
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {


    val uiState by viewModel.uiState.collectAsState()
    val profile = uiState.profile
    val selectedItem = uiState.selectedItem
    val context = LocalContext.current

    val options = UCrop.Options().apply {
        setCircleDimmedLayer(true) // Hace que el área fuera del círculo sea opaca/oscura.

        // Opcional: Personalización de la interfaz
        // ... otras opciones de UI
    }


    val cropLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val croppedUri = UCrop.getOutput(result.data!!)
            if (croppedUri != null) {
                viewModel.addImage(croppedUri.toString())
            }
        }
    }
    val cropLauncherProfileImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val croppedUri = UCrop.getOutput(result.data!!)
            if (croppedUri != null) {
                // 5. Pasar la URI final al ViewModel

                viewModel.changeProfileImage(croppedUri.toString())
            }
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { sourceUri ->
        if (sourceUri != null) {

            val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped_image_${System.currentTimeMillis()}.jpg"))

            val uCrop = UCrop.of(sourceUri, destinationUri)
                .withAspectRatio(1f, 1f)
                .withMaxResultSize(1000, 1000)

            cropLauncher.launch(uCrop.getIntent(context))
        }
    }


    val galleryLauncherProfile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { sourceUri ->
        if (sourceUri != null) {

            // 🔥 CORRECCIÓN 2: Usar 'context' para acceder al caché
            val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped_image_${System.currentTimeMillis()}.jpg"))

            val uCrop = UCrop.of(sourceUri, destinationUri)
                .withAspectRatio(1f, 1f)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)

            // 🔥 CORRECCIÓN 3: Usar 'context' para obtener el Intent
            cropLauncherProfileImage.launch(uCrop.getIntent(context))
        }
    }


    if (uiState.showDialog) {
        CustomDialog(
            showDialog = uiState.showDialog,
            onDismiss = { viewModel.toggleDialog(false) },
            onConfirm = { viewModel.confirmDialogAction() }
        )
    }

    Box(modifier = modifier.fillMaxSize()) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(255, 255, 255))
        ) {
            // Cabecera en un Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // altura fija de la cabecera
                    .background(LightBlue)
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Círculos de arriba
                    Row(
                        modifier = Modifier
                            .border(2.dp, color= DarkBlue, shape = RoundedCornerShape(30.dp))) {

                        repeat(3) {
//                        Spacer(
//                            modifier = Modifier
//                                .size(84.dp)
//                                .clip(CircleShape)
//                                .background(Color(75, 61, 61, 255))
//                        )
                            Image(painter = painterResource(R.drawable.insigniafinall), contentDescription = null, modifier= Modifier.size(80.dp))
                        }
                    }
                }

                // Followers pegados abajo
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .align(Alignment.BottomCenter) // <- aquí la magia
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ProfileFollowersFollowingInfo(number = profile.followersCount.toString(), textInfo = "Followers")
                    ProfileFollowersFollowingInfo(number = profile.followingCount.toString(), textInfo = "Following")


                }

                // Imagen que sobresale


                ProfileCircleImage(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    imageUri = profile.profileImageRes,
                    onUpdateImageClick = {
                        galleryLauncherProfile.launch("image/*")
                    }
                )

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ){
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.TopCenter) // <- aquí la magia
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(profile.name, color = Color(13, 27, 42, 255), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("ID ${profile.id}", color = Color(107, 114, 128, 255))
                    }
                }

            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 3 columnas
                modifier = Modifier
                    .padding(bottom = 80.dp, top = 50.dp, start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item{
                    ProfileAddImage(
                        onAddImageClick = {
                            galleryLauncher.launch("image/*")
                        }
                    )
                }
                itemsIndexed(profile.images) { index, imageUri ->


                        ProfileImageInfo(imageUri = imageUri, index = index , viewModel = viewModel)

                }

            }

        }


    }



}



@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (showDialog) {
            AlertDialog(
                onDismissRequest = onDismiss,
                shape = RoundedCornerShape(16.dp),
                icon = {
                    Text(
                        text = "Eliminar Foto",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        text = "¿Estás seguro de que quieres eliminar esta foto?",
                        textAlign = TextAlign.Center,
                        color = Color.Black.copy(alpha = 0.8f)
                    )
                },
                confirmButton = {
                    TextButton(onClick = onConfirm) {
                        Row(
                            modifier = Modifier
                                .background(Color(255, 61, 61, 255), shape = CircleShape)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(text = "Confirmar", color = Color.White)
                        }
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Cancelar", color = DarkBlue)
                    }
                }
            )

    }

}


@Composable
fun ProfileCircleImage(
    modifier: Modifier = Modifier,
    imageUri: String,
    onUpdateImageClick: () -> Unit

    ){
    val uri = remember(imageUri){Uri.parse(imageUri)}
    val painter = rememberAsyncImagePainter(
        model = uri,
    )

    Box(
        modifier = modifier
            .offset(y = 64.dp)
            .size(150.dp)
            .clip(CircleShape)
            .background(Color(171, 211, 250, 255)),
        contentAlignment = Alignment.Center
    ) {
        // Imagen circular
        Box(modifier = Modifier.padding( 5.dp)){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp) // cuadrado
                    .clip(CircleShape),
                contentScale = ContentScale.Crop // ajusta el recorte
            )

            // Icono de lápiz en la esquina inferior derecha
            Icon(
                painter = painterResource(id = R.drawable.pencill), // tu icono Lucide (svg/vector)
                contentDescription = "Editar",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable{
                        onUpdateImageClick()
                    }
                    .size(36.dp) // tamaño del icono
                    .offset(y = -7.dp, x = -4.dp)
                    .border(3.dp, Color(171, 211, 250, 255), shape = CircleShape)

                    .background(Color.White, CircleShape) // fondo redondo para resaltarlo
                    .padding(6.dp),


                tint = Color(0xFF2563EB)
            )

        }

    }
}

@Composable
fun ProfileFollowersFollowingInfo(
    number: String,
    textInfo: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier= Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF2563EB))
            .padding(horizontal = 16.dp, vertical = 8.dp)){
            Text(number, color = Color(0xFFFFFFFF)) // Número destacado

        }
        Spacer(modifier = Modifier.height(6.dp))

        Text(textInfo, color = Color(0xFF000000)) // Texto más sutil

    }
}


@Composable
fun ProfileAddImage(

    onAddImageClick: () -> Unit
){

    Box(
        modifier = Modifier
            .aspectRatio(4f / 3f)
            .clickable{
                onAddImageClick()
            }
            .padding(8.dp), // espacio alrededor para la sombra

        contentAlignment = Alignment.Center
    ) {
        // Círculo azul con sombra y borde suave
        Box(
            modifier = Modifier
                .fillMaxSize(0.3f) // tamaño proporcional
                .aspectRatio(1f)
                .shadow(8.dp, CircleShape) // sombra suave
                .clip(CircleShape)
                .background(Color(0xFF2563EB)) // azul para resaltar
                .placeholder(
                    visible = true,
                    color = Color(
                        171,
                        211,
                        250,
                        255
                    ).copy(alpha = 0.4f), // azul claro de fondo con transparencia
                    shape = CircleShape,
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        // Icono centrado
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir foto",
            tint = Color.White.copy(alpha = 0.9f), // blanco semiopaco para contraste
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun ProfileImageInfo(
    imageUri: String, // Recibimos la URI como String
    index: Int,
    viewModel: ProfileViewModel
) {
    val uri = remember(imageUri) { Uri.parse(imageUri) }

    val painter = rememberAsyncImagePainter(
        model = uri,
    )
    Card {

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(4f / 3f)
            .clickable(onClick = {
                viewModel.toggleDialog(true, index)

            })
    )
    }

}