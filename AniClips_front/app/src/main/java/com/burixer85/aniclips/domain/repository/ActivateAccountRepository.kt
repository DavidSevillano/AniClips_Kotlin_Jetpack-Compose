package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.UserActivateAccount

interface ActivateAccountRepository {
    suspend fun activateAccount(token: String): UserActivateAccount?
}