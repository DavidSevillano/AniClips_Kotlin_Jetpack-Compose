package com.burixer85.aniclips.data.service.auth

import com.burixer85.aniclips.data.network.auth.LoginClient
import com.burixer85.aniclips.data.request.LoginRequest
import com.burixer85.aniclips.data.response.auth.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun login(username: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            val request = LoginRequest(username, password)
            val response = loginClient.login(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}