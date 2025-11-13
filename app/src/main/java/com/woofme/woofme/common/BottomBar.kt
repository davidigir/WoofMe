package com.woofme.woofme.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.woofme.woofme.navigation.BottomNavItem
import com.woofme.woofme.navigation.ScreenRoutes
import com.woofme.woofme.ui.theme.DarkBlue
import com.woofme.woofme.ui.theme.LightBlue
import com.woofme.woofme.viewmodel.ChatViewModel
import com.woofme.woofme.viewmodel.ProfileViewModel
@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(BottomNavItem.Home, BottomNavItem.Chats, BottomNavItem.Profile, BottomNavItem.Settings)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val isViewingProfileDestination = currentRoute == ScreenRoutes.Profile


    val isViewingOtherProfile = isViewingProfileDestination &&
            navBackStackEntry?.arguments?.getString(ScreenRoutes.USER_ID) != null


    NavigationBar(
        modifier = Modifier.height(90.dp),
        containerColor = LightBlue,
    ) {
        items.forEach { item ->

            val isSelected = when (item) {

                BottomNavItem.Home -> {
                    // SELECCIÓN DE INICIO:
                    // 1. Si estamos viendo otro perfil (la excepción)
                    // O
                    // 2. Si la ruta actual coincide con Home.
                    isViewingOtherProfile || currentRoute?.startsWith(item.route) == true
                }

                BottomNavItem.Profile -> {
                    // SELECCIÓN DE PERFIL:
                    // Solo se selecciona si estamos en el destino Profile Y el argumento es nulo (Mi Perfil).
                    val isMyProfile = isViewingProfileDestination &&
                            navBackStackEntry?.arguments?.getString(ScreenRoutes.USER_ID) == null

                    isMyProfile
                }
                BottomNavItem.Chats -> {
                    currentRoute == ScreenRoutes.ChatList || currentRoute?.startsWith(ScreenRoutes.ChatDetail) == true
                }

                // Para el resto de ítems (Chats, Settings), la lógica de ruta base sigue siendo válida.
                else -> currentRoute?.startsWith(item.route) == true
            }

            NavigationBarItem(
                selected = isSelected,

                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(ScreenRoutes.ChatList) {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                },

                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected) DarkBlue else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedTextColor = DarkBlue,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}