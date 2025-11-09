package com.woofme.woofme.model

import java.time.LocalTime

data class Message(
    val text: String,
    val hour: LocalTime,
    val isUser: Boolean


)