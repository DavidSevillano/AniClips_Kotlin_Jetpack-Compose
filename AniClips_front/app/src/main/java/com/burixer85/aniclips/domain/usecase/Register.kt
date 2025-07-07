package com.burixer85.aniclips.domain.usecase

import android.util.Patterns
import com.burixer85.aniclips.domain.model.OperationResult
import com.burixer85.aniclips.domain.model.UserRegister
import com.burixer85.aniclips.domain.repository.RegisterRepository
import javax.inject.Inject

class Register @Inject constructor(val registerRepository: RegisterRepository) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): OperationResult<UserRegister> {

        if (username.isEmpty() || password.isEmpty()) {
            return OperationResult.EmptyFields
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return OperationResult.InvalidEmailFormat
        }
        if (password != verifyPassword) {
            return OperationResult.RepeatPasswordError
        }
        val user = registerRepository.register(username, email, password, verifyPassword)

        return if (user != null) OperationResult.Success(user) else return OperationResult.InvalidCredentials
    }
}