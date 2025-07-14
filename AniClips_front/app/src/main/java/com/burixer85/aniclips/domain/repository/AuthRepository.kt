package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.auth.UserActivateAccount
import com.burixer85.aniclips.domain.model.auth.UserLogin
import com.burixer85.aniclips.domain.model.auth.UserRegister

interface AuthRepository {
    suspend fun login(username: String, password: String): UserLogin?

    suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): UserRegister?

    suspend fun activateAccount(token: String): UserActivateAccount?

}