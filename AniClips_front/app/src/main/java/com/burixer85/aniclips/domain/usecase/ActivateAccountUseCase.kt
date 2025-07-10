package com.burixer85.aniclips.domain.usecase

import com.burixer85.aniclips.domain.model.OperationResult
import com.burixer85.aniclips.domain.model.UserActivateAccount
import com.burixer85.aniclips.domain.repository.ActivateAccountRepository
import javax.inject.Inject

class ActivateAccountUseCase @Inject constructor(val activateAccountRepository: ActivateAccountRepository) {
    suspend operator fun invoke(token: String): OperationResult<UserActivateAccount> {
        if (token.isEmpty()) {
            return OperationResult.EmptyFields
        }
        val user = activateAccountRepository.activateAccount(token)

        return if (user != null) OperationResult.Success(user) else OperationResult.InvalidCredentials
    }
}