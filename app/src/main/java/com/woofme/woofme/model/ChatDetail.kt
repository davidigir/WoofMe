package com.woofme.woofme.model

data class ChatDetail (
    val user: User,
    val messageList: List<Message>
)