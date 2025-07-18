package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.response.auth.ActivateAccountResponse
import com.burixer85.aniclips.data.response.auth.LoginResponse
import com.burixer85.aniclips.data.response.auth.RegisterResponse
import com.burixer85.aniclips.data.response.auth.toDomain
import com.burixer85.aniclips.data.service.auth.ActivateAccountService
import com.burixer85.aniclips.data.service.auth.LoginService
import com.burixer85.aniclips.data.service.auth.RegisterService
import com.burixer85.aniclips.domain.model.auth.UserActivateAccount
import com.burixer85.aniclips.domain.model.auth.UserLogin
import com.burixer85.aniclips.domain.model.auth.UserRegister
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val loginService: LoginService,
    private val registerService: RegisterService,
    private val activateAccountService: ActivateAccountService
) :
    AuthRepository {
    override suspend fun login(username: String, password: String): UserLogin? {
        val loginResponse: LoginResponse? = loginService.login(username, password)
        return loginResponse?.toDomain()
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): UserRegister? {
        val registerResponse: RegisterResponse? =
            registerService.register(username, email, password, verifyPassword)
        return registerResponse?.toDomain()
    }

    override suspend fun activateAccount(token: String): UserActivateAccount? {
        val activateAccountResponse: ActivateAccountResponse? =
            activateAccountService.activateAccount(token)
        return activateAccountResponse?.toDomain()
    }
}