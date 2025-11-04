package com.woofme.woofme.model

import com.woofme.woofme.R

data class Profile(
    val id: String,
    val name: String,
    val followersCount: Int,
    val followingCount: Int,
    val profileImageRes: Int,
    val images: List<String>
)

object ProfileData {
    // Usamos el ID de recurso (R.drawable.xxx) que ya tienes
    fun getPreloadedProfile(): Profile {
        return Profile(
            id = "#123123",
            name = "Pepe",
            followersCount = 123,
            followingCount = 123123,
            profileImageRes = R.drawable.perro3, // Asumiendo que 'perro3' es la foto de perfil
            images = emptyList() // Asumiendo que 'perro2' son las fotos del muro
        )
    }
}