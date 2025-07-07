package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.UserLogin

interface AuthRepository {
    suspend fun login(username: String, password: String): UserLogin?
}