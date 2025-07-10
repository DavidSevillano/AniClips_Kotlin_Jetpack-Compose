package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.service.ActivateAccountService
import com.burixer85.aniclips.domain.model.UserActivateAccount
import com.burixer85.aniclips.domain.repository.ActivateAccountRepository
import javax.inject.Inject

class ActivateAccountRepositoryImp @Inject constructor(private val activateAccountService: ActivateAccountService) :
    ActivateAccountRepository {
    override suspend fun activateAccount(token: String): UserActivateAccount? {
        val response = activateAccountService.activateAccount(token)
        return response?.let {
            UserActivateAccount(
                id = it.id,
                role = it.roles,
                username = it.username
            )
        }
    }
}