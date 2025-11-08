package com.woofme.woofme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object ScreenRoutes {
    const val ChatList = "chatlist_route"
    const val Profile = "profile_route"
    const val Settings = "settings_route"

    const val ChatDetail = "chatdetail_route"
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Chats : BottomNavItem(ScreenRoutes.ChatList, Icons.Default.Home, "Inicio")
    object Profile : BottomNavItem(ScreenRoutes.Profile, Icons.Default.Face, "Perfil")
    object Settings : BottomNavItem(ScreenRoutes.Settings, Icons.Default.Settings, "Ajustes")
}