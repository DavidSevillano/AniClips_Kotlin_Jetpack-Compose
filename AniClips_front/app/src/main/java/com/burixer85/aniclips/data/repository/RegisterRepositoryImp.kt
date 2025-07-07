package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.service.RegisterService
import com.burixer85.aniclips.domain.model.UserRegister
import com.burixer85.aniclips.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(private val registerService: RegisterService) :
    RegisterRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): UserRegister? {
        val response = registerService.register(username, email, password, verifyPassword)
        return response?.let {
            UserRegister(
                id = it.id,
                username = it.username,
                role = it.roles,
            )
        }
    }
}