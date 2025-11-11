package com.woofme.woofme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object ScreenRoutes {
    const val ChatList = "chatlist_route"

    const val Home = "home_route"
    const val Settings = "settings_route"

    const val ChatDetail = "chatdetail_route"
    const val ProfileBaseRoute = "profile_route"
    const val USER_ID = "userId"

    const val Profile = "$ProfileBaseRoute?$USER_ID={${USER_ID}}"

    fun profileWithId(userId: String) = "$ProfileBaseRoute?$USER_ID=$userId"
    fun myProfile() = ProfileBaseRoute
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem(ScreenRoutes.myProfile(), Icons.Default.Home, "Inicio")
    object Chats : BottomNavItem(ScreenRoutes.ChatList, Icons.AutoMirrored.Filled.Chat, "Chats")
    object Profile : BottomNavItem(ScreenRoutes.Profile, Icons.Default.Face, "Perfil")
    object Settings : BottomNavItem(ScreenRoutes.Settings, Icons.Default.Settings, "Ajustes")
}