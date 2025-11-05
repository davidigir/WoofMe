package com.woofme.woofme.model

import com.woofme.woofme.R

const val APP_PACKAGE_NAME = "com.woofme.woofme"

data class Profile(
    val id: String,
    val name: String,
    val followersCount: Int,
    val followingCount: Int,
    val profileImageRes: String,
    val images: List<String>
)

object ProfileData {
    // Usamos el ID de recurso (R.drawable.xxx) que ya tienes

    private fun resourceIdToUriString(resourceId: Int):String{
        return "android.resource://$APP_PACKAGE_NAME/$resourceId"
    }
    fun getPreloadedProfile(): Profile {
        val initialImages = List(3){
            resourceIdToUriString(R.drawable.perro2)
        }
        val profileImageUri = resourceIdToUriString(R.drawable.perro3)
        return Profile(
            id = "#123123",
            name = "Pepe",
            followersCount = 123,
            followingCount = 123123,
            profileImageRes = profileImageUri, // Asumiendo que 'perro3' es la foto de perfil
            images = initialImages // Asumiendo que 'perro2' son las fotos del muro
        )
    }
}