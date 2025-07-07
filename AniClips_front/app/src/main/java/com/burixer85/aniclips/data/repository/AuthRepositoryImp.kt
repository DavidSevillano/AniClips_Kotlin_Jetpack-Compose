package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.service.LoginService
import com.burixer85.aniclips.domain.model.UserLogin
import com.burixer85.aniclips.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val loginService: LoginService) :
    AuthRepository {
    override suspend fun login(username: String, password: String): UserLogin? {
        val response = loginService.login(username, password)
        return response?.let {
            UserLogin(
                id = it.id,
                username = it.username,
                avatar = it.avatar,
                role = it.roles,
                token = it.token
            )
        }
    }
}