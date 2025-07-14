package com.burixer85.aniclips.domain.usecase.auth

import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.model.auth.UserLogin
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): OperationResultAuth<UserLogin> {

        if (username.isEmpty() || password.isEmpty()) {
            return OperationResultAuth.EmptyFields
        }
        val user = authRepository.login(username, password)

        return if (user != null) OperationResultAuth.Success(user) else OperationResultAuth.InvalidCredentials
    }
}