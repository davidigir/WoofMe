package com.woofme.woofme.model

data class User(
    val id: String,
    val name: String,
    val followersCount: Int,
    val followingCount: Int,

)
