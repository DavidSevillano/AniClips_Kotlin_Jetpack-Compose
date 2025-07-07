package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.UserRegister

interface RegisterRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): UserRegister?
}