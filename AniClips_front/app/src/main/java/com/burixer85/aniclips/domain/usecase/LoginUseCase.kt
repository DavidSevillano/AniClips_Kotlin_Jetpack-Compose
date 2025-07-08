package com.burixer85.aniclips.domain.usecase

import com.burixer85.aniclips.domain.model.OperationResult
import com.burixer85.aniclips.domain.model.UserLogin
import com.burixer85.aniclips.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String): OperationResult<UserLogin> {

        if (username.isEmpty() || password.isEmpty()) {
            return OperationResult.EmptyFields
        }
        val user = loginRepository.login(username, password)

        return if (user != null) OperationResult.Success(user) else OperationResult.InvalidCredentials
    }
}