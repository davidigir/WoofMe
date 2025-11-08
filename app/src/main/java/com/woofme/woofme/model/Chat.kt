package com.woofme.woofme.model

import com.woofme.woofme.R

data class Chat(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val profileImageRes: String,
    val typeChat: ChatType, //si es grupo o contacto
)

object ChatDataMock {

    // Función auxiliar para construir la URI de recurso local
    private fun resourceIdToUriString(resourceId: Int): String {
        return "android.resource://$APP_PACKAGE_NAME/$resourceId"
    }

    fun getInitialChats(): List<Chat> {
        val profileImageUri = resourceIdToUriString(R.drawable.perro3)
        val profileImageUri2 = resourceIdToUriString(R.drawable.perro2)

        return listOf(
            Chat(
                id = 1,
                name = "Pepe",
                lastMessage = "Ok nos vemos allí",
                lastMessageTime = "09:38 AM",
                profileImageRes = profileImageUri,
                typeChat = ChatType.CONTACT
            ),
            Chat(
                id = 2,
                name = "Luna",
                lastMessage = "Mañana a las 10:00",
                lastMessageTime = "08:15 AM",
                profileImageRes = profileImageUri2,
                typeChat = ChatType.GROUP
            ),
//            Chat(
//                id = 3,
//                name = "API User",
//                lastMessage = "Prueba de URL",
//                lastMessageTime = "07:00 AM",
//                profileImageRes = "https://picsum.photos/48"
//            )
        )
    }
}