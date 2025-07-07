package com.burixer85.aniclips.data.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val verifyPassword: String
)
