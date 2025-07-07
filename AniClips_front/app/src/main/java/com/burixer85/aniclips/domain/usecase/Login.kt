package com.burixer85.aniclips.domain.usecase

import com.burixer85.aniclips.domain.model.UserLogin
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): UserLogin? {

        if (username.isEmpty() || password.isEmpty()) {
        }
        return authRepository.login(username, password)
    }
}