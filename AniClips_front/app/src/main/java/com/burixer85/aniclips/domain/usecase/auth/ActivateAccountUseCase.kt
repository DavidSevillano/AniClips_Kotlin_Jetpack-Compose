package com.burixer85.aniclips.domain.usecase.auth

import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.model.auth.UserActivateAccount
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class ActivateAccountUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend operator fun invoke(token: String): OperationResultAuth<UserActivateAccount> {
        if (token.isEmpty()) {
            return OperationResultAuth.EmptyFields
        }
        val user = authRepository.activateAccount(token)

        return if (user != null) OperationResultAuth.Success(user) else OperationResultAuth.InvalidCredentials
    }
}