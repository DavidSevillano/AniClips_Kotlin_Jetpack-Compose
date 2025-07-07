package com.burixer85.aniclips.data.service

import com.burixer85.aniclips.data.network.RegisterClient
import com.burixer85.aniclips.data.request.RegisterRequest
import com.burixer85.aniclips.data.response.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterService @Inject constructor(private val registerClient: RegisterClient) {

    suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): RegisterResponse? {
        return withContext(Dispatchers.IO) {
            val request = RegisterRequest(username, email, password, verifyPassword)
            val response = registerClient.register(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

}