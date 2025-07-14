package com.burixer85.aniclips.domain.usecase.auth

import android.util.Patterns
import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.model.auth.UserRegister
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): OperationResultAuth<UserRegister> {

        if (username.isEmpty() || password.isEmpty()) {
            return OperationResultAuth.EmptyFields
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return OperationResultAuth.InvalidEmailFormat
        }
        if (password != verifyPassword) {
            return OperationResultAuth.RepeatPasswordError
        }
        val user = authRepository.register(username, email, password, verifyPassword)

        return if (user != null) OperationResultAuth.Success(user) else return OperationResultAuth.InvalidCredentials
    }
}